/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbai4tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import  net.minidev.json.*;

/**
 *
 * @author Admin
 */
public class myFrame extends JFrame {

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTree folderTree;
    private List<Item> items;
    private static myFrame instance ;
    public static myFrame Instance()
    {
        return instance;
    }
    public List<Item> getItems() {
      
        return items;
    }

    public myFrame() {
      
        super("Bai 4");
       
        instance = this;
        items = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initLayout();
        setSize(800, 600);
        setVisible(true);

    }
    TreeModelEx treeModel;

    private void initLayout() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(150,0));
        leftPanel.setBackground(Color.WHITE);

        folderTree = new JTree();
        treeModel = new TreeModelEx(buildTree());

        folderTree.setModel(treeModel);
        leftPanel.add(folderTree);
 
        folderTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode tree = (DefaultMutableTreeNode) folderTree.getLastSelectedPathComponent();
                loadItems(e.getPath().getPath()[1].toString());
                updateItems(tree);

            }
        });

        rightPanel = new JPanel();

        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightPanel.setBackground(Color.WHITE);

        rightPanel.setPreferredSize(new Dimension(1, 0));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        //splitPane.setContinuousLayout(true); // Lệnh này dùng để fix cứng 2 panel

        add(splitPane, BorderLayout.CENTER);
        clickeven = new ItemClickListener(this);
    }
    private ItemClickListener clickeven;

    public void updateItems(DefaultMutableTreeNode e) {
        rightPanel.removeAll();
        rightPanel.repaint();
        for (Item item : items) {
            rightPanel.add(item);

            item.addMouseListener(clickeven);

            if (e != null) {
                if (item.isFolder()) {
                    DefaultMutableTreeNode doc = new DefaultMutableTreeNode(item.getText());
                    treeModel.insertNodeInto(doc, e, 0);
                }
            }
           
        }
treeModel.reload();
    }

    public void loadItems(String path) {

        docFile(path);
    }
    DefaultMutableTreeNode root;

    public TreeNode buildTree() {

        root = new DefaultMutableTreeNode("Computer");

        for (File i : File.listRoots()) {

            DefaultMutableTreeNode doc = new DefaultMutableTreeNode(i.getAbsolutePath());

            root.add(doc);

        }

        return root;
    }

    private void docFile(String path) {
        items.clear();
        File[] f = new File(path).listFiles();
        if (f==null) return;
        for (File i : f) {
            items.add(new Item(i.getName(), i.isDirectory()).setPath(i.getAbsolutePath()));

        }
    }
}

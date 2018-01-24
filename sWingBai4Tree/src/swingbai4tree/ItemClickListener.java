/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbai4tree;

import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class ItemClickListener implements MouseListener {

    private myFrame container;

    public ItemClickListener(JFrame container) {
        this.container = (myFrame) container;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Item item = (Item) e.getSource();
       
        myFrame.Instance().loadItems(item.getPath());
        myFrame.Instance().updateItems(null);
        item.setSeleted(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("just press!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("mouse eneterd");
        Item item = (Item) e.getSource();
        item.setBackground(Color.red);
        item.setOpaque(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("mouse exited");
        Item item = (Item) e.getSource();
        item.setBackground(new Color(0, 0, 0, 0));
        item.setOpaque(false);
    }

}

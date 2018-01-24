/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbai4tree;

import javax.swing.UIManager;

/**
 *
 * @author Admin
 */
public class SWingBai4Tree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
            new myFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

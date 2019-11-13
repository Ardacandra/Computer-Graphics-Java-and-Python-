/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeanimation;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author Ardacandra Subiantoro-18/427572/PA/18532
 */
public class TreeAnimation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> TreeAnimation.run());
    }
    
    public static void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setTitle("Ardacandra Subiantoro-18/427572/PA/18532");
        
        f.getContentPane().add(new FractalTree());
        f.pack();
        
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
    }
}

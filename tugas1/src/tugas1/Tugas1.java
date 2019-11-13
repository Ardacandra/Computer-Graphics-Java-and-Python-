/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas1;

import java.awt.Color;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author Ardacandra Subiantoro (18/427572/PA/18532)
 */
public class Tugas1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukan koordinat titik(Rekomendasi:x1=200, y1=200, x2=500, y2=400, r=50");
        System.out.print("Masukan koordinat x1:");
        int x1 = scan.nextInt();
        System.out.print("Masukan koordinat y1:");
        int y1 = scan.nextInt();
        System.out.print("Masukan koordinat x2:");
        int x2 = scan.nextInt();
        System.out.print("Masukan koordinat y2:");
        int y2 = scan.nextInt();
        System.out.print("Masukan radius:");
        int r = scan.nextInt();
        Color c = Color.WHITE;
        
        SwingUtilities.invokeLater(() -> Tugas1.run(x1, y1, x2, y2, r, c));
    }
    
    public static void run(int x1, int y1, int x2, int y2, int r, Color c) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setTitle("Tugas 1-Ardacandra S.");
        
        f.getContentPane().add(new Jangkar(x1, y1, x2, y2, r, c));
        f.pack();
        
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

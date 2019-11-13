/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dreflection;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Ardacandra Subiantoro(18/427572/PA/18532)
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    //untuk memilih antara koordinat 1 dan 2
    int switcher = 1;
    //koordinat untuk titik
    int x1L = 0;
    int y1L = 0;
    int x2L = 0;
    int y2L = 0;
    //koordinat untuk segitiga
    int x1T = 300;
    int y1T = 200;
    int x2T = 300;
    int y2T = 150;
    int x3T = 400;
    int y3T = 150;
    
    public class Viewport extends JPanel {
        //segitiga dan garis yang akan digambar disimpan di List
        private List<Object> shapes = new ArrayList<>();
        
        public Viewport() {
            setPreferredSize(new Dimension(800, 600));
            setBackground(Color.BLACK);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            shapes.forEach((s) -> {
                if (s instanceof Triangle) {
                    ((Triangle)s).draw(g);
                } else if(s instanceof ReflectionLine) {
                    ((ReflectionLine)s).draw(g);
                }
            });
        }
    }
    
    public Main(){
        
        //VIEWPORT
        Viewport vp = new Viewport();
        vp.shapes.add(new Triangle(x1T, y1T, x2T, y2T, x3T, y3T));
        
        //CONTROL PANEL
        //menulis instruksi
        JTextArea instruksi = new JTextArea();
        instruksi.setFont(new Font("Serif", Font.PLAIN, 15));
        instruksi.setText("Klik 2 titik pada layar hitam untuk membuat garis refleksi. \nTekan tombol Reset untuk menampilkan gambar awal.");
        instruksi.setEditable(false);
        instruksi.setLineWrap(true);
        
        //menunjukkan koordinat titik
        JLabel titik1 = new JLabel();
        titik1.setText(" Titik 1:");
        JTextField koordinatX1 = new JTextField("", 30);
        koordinatX1.setEditable(false);
        JTextField koordinatY1 = new JTextField("", 30); 
        koordinatY1.setEditable(false);
        
        JLabel titik2 = new JLabel();
        titik2.setText(" Titik 2:");   
        JTextField koordinatX2 = new JTextField("", 30);
        koordinatX2.setEditable(false);
        JTextField koordinatY2 = new JTextField("", 30);
        koordinatY2.setEditable(false);

        JLabel titikMouse = new JLabel();
        titikMouse.setText(" Titik Mouse:");   
        JTextField koordinatXMouse = new JTextField("", 30);
        koordinatXMouse.setEditable(false);
        JTextField koordinatYMouse = new JTextField("", 30);
        koordinatYMouse.setEditable(false);
        
        JLabel x1Label = new JLabel();
        x1Label.setText(" X :");
        JLabel y1Label = new JLabel();
        y1Label.setText(" Y :");
        
        JLabel x2Label = new JLabel();
        x2Label.setText(" X :");
        JLabel y2Label = new JLabel();
        y2Label.setText(" Y :");

        JLabel xMouseLabel = new JLabel();
        xMouseLabel.setText(" X :");
        JLabel yMouseLabel = new JLabel();
        yMouseLabel.setText(" Y :");
        
        //menggabungkan koordinat dengan label nya
        SpringLayout layout = new SpringLayout();
        
        layout.putConstraint(SpringLayout.WEST, koordinatX1, 5, SpringLayout.EAST, x1Label);
        JPanel wrapper1 = new JPanel(layout);
        wrapper1.add(x1Label);
        wrapper1.add(koordinatX1);
        
        layout.putConstraint(SpringLayout.WEST, koordinatY1, 5, SpringLayout.EAST, y1Label);
        JPanel wrapper2 = new JPanel(layout);
        wrapper2.add(y1Label);
        wrapper2.add(koordinatY1);
        
        layout.putConstraint(SpringLayout.WEST, koordinatX2, 5, SpringLayout.EAST, x2Label);
        JPanel wrapper3 = new JPanel(layout);
        wrapper3.add(x2Label);
        wrapper3.add(koordinatX2);
 
        layout.putConstraint(SpringLayout.WEST, koordinatY2, 5, SpringLayout.EAST, y2Label);
        JPanel wrapper4 = new JPanel(layout);
        wrapper4.add(y2Label);
        wrapper4.add(koordinatY2);

        layout.putConstraint(SpringLayout.WEST, koordinatXMouse, 5, SpringLayout.EAST, xMouseLabel);
        JPanel wrapper5 = new JPanel(layout);
        wrapper5.add(xMouseLabel);
        wrapper5.add(koordinatXMouse);
        
        layout.putConstraint(SpringLayout.WEST, koordinatYMouse, 5, SpringLayout.EAST, yMouseLabel);
        JPanel wrapper6 = new JPanel(layout);
        wrapper6.add(yMouseLabel);
        wrapper6.add(koordinatYMouse);
        
        JPanel koordinat = new JPanel(new GridLayout(9, 1));
        koordinat.add(titik1);
        koordinat.add(wrapper1);
        koordinat.add(wrapper2);
        koordinat.add(titik2);
        koordinat.add(wrapper3);
        koordinat.add(wrapper4);
        koordinat.add(titikMouse);
        koordinat.add(wrapper5);
        koordinat.add(wrapper6);
        
        //menampilkan tombol reset
        JButton reset = new JButton("Reset");
        
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setPreferredSize(new Dimension(400, 600));
        controlPanel.add(instruksi, BorderLayout.NORTH);
        controlPanel.add(koordinat, BorderLayout.CENTER);
        controlPanel.add(reset, BorderLayout.SOUTH);
        
        //MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(vp, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.EAST);
        
        //FRAME
        JFrame frame = new JFrame();
        frame.setTitle("Tugas 3-Ardacandra Subiantoro");
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setSize(new Dimension(1200, 600));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        //TIMER untuk cek koordinat mouse
        Timer timer = new Timer(100, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, vp);
                koordinatXMouse.setText(Integer.toString(p.x));
                koordinatYMouse.setText(Integer.toString(p.y));
            }
        });
        timer.start();
        
        //ACTION LISTENER
        vp.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
                if(switcher == 1){
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(p, vp);
                    x1L = p.x;
                    y1L = p.y;
                    koordinatX1.setText(Integer.toString(p.x));
                    koordinatY1.setText(Integer.toString(p.y));
                    switcher = 2;
                } else {
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(p, vp);
                    x2L = p.x;
                    y2L = p.y;
                    koordinatX2.setText(Integer.toString(p.x));
                    koordinatY2.setText(Integer.toString(p.y));
                    switcher = 1;
                    
                    vp.shapes.add(new ReflectionLine(x1L, y1L, x2L, y2L));
                    int[] reflectionCoordinates = getReflectionCoordinates(x1T, y1T, x2T, y2T, x3T, y3T, x1L, y1L, x2L, y2L);
                    x1T = reflectionCoordinates[0];
                    y1T = reflectionCoordinates[1];
                    x2T = reflectionCoordinates[2];
                    y2T = reflectionCoordinates[3];
                    x3T = reflectionCoordinates[4];
                    y3T = reflectionCoordinates[5]; 
                    vp.shapes.add(new Triangle(x1T, y1T, x2T, y2T, x3T, y3T));
                    vp.repaint();
                }
            }
        });
        
        reset.addActionListener((ActionEvent e) -> {
            vp.shapes.clear();
            x1T = 300;
            y1T = 200;
            x2T = 300;
            y2T = 150;
            x3T = 400;
            y3T = 150;
            x1L = 0;
            y1L = 0;
            x2L = 0;
            y2L = 0;
            koordinatX1.setText("");
            koordinatY1.setText("");
            koordinatX2.setText("");
            koordinatY2.setText("");
            vp.shapes.add(new Triangle(x1T, y1T, x2T, y2T, x3T, y3T));
            vp.repaint();
            switcher = 1;
        });
    }
    //fungsi untuk mendapatkan koordinat refleksi 
    private int[] getReflectionCoordinates(int x1T, int y1T, int x2T, int y2T, int x3T, int y3T, int x1L, int y1L, int x2L, int y2L) {
        int[] hasil = new int[6];
        
        double dy = y2L-y1L;
        double dx = x2L-x1L;
        
        Matrix m1 = new Matrix(x1T, y1T);
        Matrix m2 = new Matrix(x2T, y2T);
        Matrix m3 = new Matrix(x3T, y3T);
        
        m1 = m1.translate(-x1L, -y1L).rotate(true, dx, dy).scale(1, -1).rotate(false, dx, dy).translate(x1L, y1L);
        m2 = m2.translate(-x1L, -y1L).rotate(true, dx, dy).scale(1, -1).rotate(false, dx, dy).translate(x1L, y1L);
        m3 = m3.translate(-x1L, -y1L).rotate(true, dx, dy).scale(1, -1).rotate(false, dx, dy).translate(x1L, y1L);
        
        hasil[0] = (int)Math.round(m1.getX());
        hasil[1] = (int)Math.round(m1.getY());
        hasil[2] = (int)Math.round(m2.getX());
        hasil[3] = (int)Math.round(m2.getY());
        hasil[4] = (int)Math.round(m3.getX());
        hasil[5] = (int)Math.round(m3.getY());
        
        return hasil;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {new Main();});
    }
    
}

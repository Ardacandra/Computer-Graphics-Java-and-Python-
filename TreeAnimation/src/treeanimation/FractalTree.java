/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeanimation;

/**
 *
 * @author Ardacandra Subiantoro-18/427572/PA/18532
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class FractalTree extends JPanel{

    int xwmin = -3;
    int xwmax = 3;
    int ywmin = 0;
    int ywmax = 7;
    int xvmin = 0;
    int xvmax = 600;
    int yvmin = 0;
    int yvmax = 700;
    
    BufferedImage img;
    
    public FractalTree() {
        setPreferredSize(new Dimension(xvmax, yvmax));
        setBackground(Color.WHITE);
        img = new BufferedImage(xvmax-xvmin+1, yvmax-yvmin+1, BufferedImage.TYPE_INT_ARGB);
        createTree();
    }
    
    void createTree(){
        int alpha = 5;
        int beta = -50;
        int gamma = 40;
        //4 Transformasi Affine
        double[] a = new double[5];
        double[] b = new double[5];
        double[] c = new double[5];
        double[] d = new double[5];
        double[] e = new double[5];
        double[] f = new double[5];
        double[] p = new double[5];
        a[1] = 0; b[1] = 0; c[1] = 0; d[1] = 0.37; e[1] = 0; f[1] = 0;
        a[2] = 0.65*Math.cos(Math.toRadians(alpha)); b[2] = -0.65*Math.sin(Math.toRadians(alpha));
        c[2] = 0.65*Math.sin(Math.toRadians(alpha)); d[2] = 0.65*Math.cos(Math.toRadians(alpha));  e[2] = 0;  f[2] = 2.5;
        a[3] = 0.5*Math.cos(Math.toRadians(beta)); b[3] = -0.5*Math.sin(Math.toRadians(beta));
        c[3] = 0.5*Math.sin(Math.toRadians(beta)); d[3] = 0.5*Math.cos(Math.toRadians(beta)); e[3] = -0.13; f[3] = 1.5;
        a[4] = 0.5*Math.cos(Math.toRadians(gamma)); b[4] = -0.5*Math.sin(Math.toRadians(gamma));
        c[4] = 0.5*Math.sin(Math.toRadians(gamma)); d[4] = 0.5*Math.cos(Math.toRadians(gamma)); e[4] = 0.13; f[4] = 1.7;
        //untuk menentukan probabilitas Iterasi Random
        double s = 0;
        for(int i=1; i<=4; i++){
            s += Math.abs(a[i]*d[i]-b[i]*c[i]);
        }
        for(int i=1; i<=4; i++){
            if(a[i]*d[i]-b[i]*c[i] == 0)
                p[i] = 0.001;
            else
                p[i] = Math.abs(a[i]*d[i]-b[i]*c[i])/s;
        }
        double x = 0, y = 0;
        //Menggambar Tree dengan menggunakan Iterasi Random
        for (int i=0; i<100000000; i++){
            double tmpx, tmpy;
            double r = Math.random();
            //menggambar batang
            if (r <= p[1]){
                tmpx = a[1]*x+b[1]*y+e[1];
                tmpy = c[1]*x+d[1]*y+f[1];
                x = tmpx;
                y = tmpy;
                if(i > 10)
                    img.setRGB(windowToViewportX(x), windowToViewportY(y), new Color(128, 128, 0).getRGB());
            }
            //menggambar daun dan tangkai
            else if (r <= p[1]+p[2]) {
                tmpx = a[2]*x+b[2]*y+e[2];
                tmpy = c[2]*x+d[2]*y+f[2];
                x = tmpx;
                y = tmpy;
                if(i > 10)
                    img.setRGB(windowToViewportX(x), windowToViewportY(y), Color.GREEN.getRGB());
            }
            else if (r <= p[1]+p[2]+p[3]) {
                tmpx = a[3]*x+b[3]*y+e[3];
                tmpy = c[3]*x+d[3]*y+f[3];
                x = tmpx;
                y = tmpy;
                if(i > 10)
                    img.setRGB(windowToViewportX(x), windowToViewportY(y), Color.GREEN.getRGB());
            }
            else {
                tmpx = a[4]*x+b[4]*y+e[4];
                tmpy = c[4]*x+d[4]*y+f[4];
                x = tmpx;
                y = tmpy;
                if(i > 10)
                    img.setRGB(windowToViewportX(x), windowToViewportY(y), Color.GREEN.getRGB());
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
 
        g.drawImage(img, 0, 0, null);
    }
 
    int windowToViewportX(double xw){
        int xv = xvmin + (int)Math.round((xw-xwmin)*(xvmax-xvmin)/(xwmax-xwmin));
        return xv;
    }
    
    int windowToViewportY(double yw){
        int yv = yvmax - (int)Math.round((yw-ywmin)*(yvmax-yvmin)/(ywmax-ywmin));
        return yv;
    }
    
    double viewportToWindowX(int xv) {
        double xw = xwmin + (xv-xvmin)*(xwmax-xwmin)/(xvmax-xvmin);
        return xw;
    }
    
    double viewportToWindowY(int yv) {
        double yw = ywmin + (yvmax-yv)*(ywmax-ywmin)/(yvmax-yvmin);
        return yw;
    }
}

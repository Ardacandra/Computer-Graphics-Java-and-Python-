/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Ardacandra Subiantoro(18/427572/PA/18532)
 */
public class Jangkar extends JPanel{
    int x1, y1, x2, y2, r;
    Color c;
    int time = 0;
    
    Jangkar(int x1, int y1, int x2, int y2, int r, Color c) {
        setPreferredSize(new Dimension(1000, 500));
        setBackground(Color.BLACK);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.r = r;
        this.c = c;
        Timer timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                repaint();
            }
        });
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        drawJangkar(g, x1, y1, x2, y2, r, c);
    }
    
    void drawJangkar(Graphics g, int x1, int y1, int x2, int y2, int r, Color c){
        g.setColor(c);
        double deltaX = x2-x1;
        double deltaY = y2-y1;
        
        //MENGGAMBAR LINGKARAN PERTAMA
        double circleGradient = (-1/(deltaY/deltaX));
        //supaya tidak ada 1/0
        if(deltaY==0 && deltaX>0)
            circleGradient = -999999;
        else if(deltaY==0 && deltaX<0)
            circleGradient = 999999;
        else if(deltaY==0 && deltaX==0)
            circleGradient = 0;
        //rumus menentukan pusat lingkaran
        double centerX;
        double centerY;
        if((deltaY<0 && deltaX>0) || (deltaY>0 && deltaX<0) || (deltaY==0 && deltaX<0)){
            centerX = x1 + r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
            centerY = y1 + circleGradient * r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
        } else if (deltaY==0 && deltaX==0){
            centerX = x1 - r;
            centerY = y1;
        } else {
            centerX = x1 - r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
            centerY = y1 - circleGradient * r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
        }
        //mencari sudut kemiringan
        double sudutKemiringan;
        sudutKemiringan = Math.toDegrees(Math.atan(deltaY/deltaX)) - 90;
        //menggambar lingkaran
        int x, y;
        if(deltaX>0 || (deltaX==0 && deltaY>0)){
            x = (int) (r*Math.cos(Math.toRadians(sudutKemiringan)) + centerX);
            y = (int) (r*Math.sin(Math.toRadians(sudutKemiringan)) + centerY);
            for(int i=(int)sudutKemiringan+1 ; i<=(int)sudutKemiringan+360 && time>=i-(int)sudutKemiringan+1 ; i++){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);
            }
        } else if(deltaX==0 && deltaY<0){
            x = (int) (r*Math.cos(Math.toRadians(sudutKemiringan-180)) + centerX);
            y = (int) (r*Math.sin(Math.toRadians(sudutKemiringan-180)) + centerY);
            for(int i=(int)sudutKemiringan-179 ; i>=(int)sudutKemiringan-540 && time>=(int)sudutKemiringan-179-i; i--){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);
            }           
        } else if(deltaX==0 && deltaY==0){
            x = x1;
            y = y1;
            for(int i=0 ; i<= 360 && time>=i; i++){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);
            }                    
        } else{
            x = (int) (r*Math.cos(Math.toRadians(sudutKemiringan)) + centerX);
            y = (int) (r*Math.sin(Math.toRadians(sudutKemiringan)) + centerY);
            for(int i=(int)sudutKemiringan-1 ; i>=(int)sudutKemiringan-360 && time>=(int)sudutKemiringan-1-i; i--){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);          
            }
        }
        //MENGGAMBAR GARIS
        x = x1;
        y = y1;
        //jika delta X lebih besar dari delta Y
        if(Math.abs(deltaX) > Math.abs(deltaY)){
            double constant = y - (deltaY/deltaX * x);
            //jika delta X positif
            if(deltaX >= 0){
                while(x<x2 && time>=360+x-x1) {
                    int x0 = x;
                    int y0 = y;
                    x++;
                    y = (int)(deltaY/deltaX * x + constant);
                    g.drawLine(x0, y0, x, y);
                }
            } else {
                //jika delta X negatif
                while(x>x2 && time>=360+x1-x) {
                    int x0 = x;
                    int y0 = y;
                    x--;
                    y = (int)(deltaY/deltaX * x + constant);
                    g.drawLine(x0, y0, x, y);
                }                
            }
        } else { //jika delta Y lebih besar sama dengan dari delta X
            double constant = x - (deltaX/deltaY * y);
            //jika delta Y positif
            if(deltaY >= 0){
                while(y < y2 && time>=360+y-y1) {
                    int x0 = x;
                    int y0 = y;
                    y++;
                    x = (int)(deltaX/deltaY * y + constant);
                    g.drawLine(x0, y0, x, y);
                }
            } else {
                //jika delta Y negatif
                while(y>y2 && time>=360+y1-y) {
                    int x0 = x;
                    int y0 = y;
                    y--;
                    x = (int)(deltaX/deltaY * y + constant);
                    g.drawLine(x0, y0, x, y);
                }                
            }           
        }
        //MENGGAMBAR LINGKARAN KEDUA
        //rumus menentukan pusat lingkaran
        if((deltaY<0 && deltaX>0) || (deltaY>0 && deltaX<0) || (deltaY==0 && deltaX<0)){
            centerX = x2 - r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
            centerY = y2 - circleGradient * r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
        } else if(deltaY==0 && deltaX==0) {
            centerX = x2+r;
            centerY = y2;
        } else {
            centerX = x2 + r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
            centerY = y2 + circleGradient * r * Math.sqrt(1/(1 + Math.pow(circleGradient, 2)));
        }
        //mencari sudut kemiringan
        sudutKemiringan = 90 - Math.toDegrees(Math.atan(deltaY/-deltaX));
        //menggambar lingkaran 
        int biggerDelta = Math.abs((int)deltaX) >= Math.abs((int)deltaY)? Math.abs((int)deltaX) : Math.abs((int)deltaY);
        if(deltaX>0 || (deltaX==0 && deltaY>0)){
            x = (int) (r*Math.cos(Math.toRadians(sudutKemiringan)) + centerX);
            y = (int) (r*Math.sin(Math.toRadians(sudutKemiringan)) + centerY);
            for(int i=(int)sudutKemiringan-1 ; i>=(int)sudutKemiringan-360 && time>=360+biggerDelta+(int)sudutKemiringan-1-i; i--){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);
            }        
        } else if(deltaX==0 && deltaY<0) {
            x = (int) (r*Math.cos(Math.toRadians(sudutKemiringan-180)) + centerX);
            y = (int) (r*Math.sin(Math.toRadians(sudutKemiringan-180)) + centerY);
            for(int i=(int)sudutKemiringan-179 ; i<=(int)sudutKemiringan+180 && time>=360+biggerDelta+i-((int)sudutKemiringan-179); i++){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);  
            }        
        } else if(deltaX==0 && deltaY==0) {
            x = x2;
            y = y2;
            for(int i=180 ; i>=-180 && time>=360+biggerDelta+180-i; i--){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);
            }                      
        } else {
            x = (int) (r*Math.cos(Math.toRadians(sudutKemiringan)) + centerX);
            y = (int) (r*Math.sin(Math.toRadians(sudutKemiringan)) + centerY);
            for(int i=(int)sudutKemiringan+1 ; i<=(int)sudutKemiringan+360 && time>=360+biggerDelta+i-(int)sudutKemiringan; i++){
                int x0 = x;
                int y0 = y;
                x = (int) (r*Math.cos(Math.toRadians(i)) + centerX);
                y = (int) (r*Math.sin(Math.toRadians(i)) + centerY);
                g.drawLine(x0, y0, x, y);  
            }
        }
    }
}

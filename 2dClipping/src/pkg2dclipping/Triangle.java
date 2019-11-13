/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dclipping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Ardacandra Subiantoro(18/427572/PA/18532)
 */
public class Triangle {
    
    private boolean clipped = false;
    private boolean rejected = false;
    //1 clipper untuk tiap garis
    Clipper clipper1;
    Clipper clipper2;
    Clipper clipper3;
    
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, int xMin, int xMax, int yMin, int yMax){   
        clipper1 = new Clipper(x1, y1, x2, y2, xMin, xMax, yMin, yMax);
        clipper2 = new Clipper(x1, y1, x3, y3, xMin, xMax, yMin, yMax);
        clipper3 = new Clipper(x2, y2, x3, y3, xMin, xMax, yMin, yMax);
        if(clipper1.isRejected() && clipper2.isRejected() && clipper3.isRejected()){
            rejected = true; 
        }
        if(clipper1.isClipped() || clipper2.isClipped() || clipper3.isClipped()){
            clipped = true;   
        }
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        if(rejected)
            return;
        if(!clipper1.isRejected())
            g2d.drawLine(clipper1.getClippedCoordinates()[0], clipper1.getClippedCoordinates()[1], clipper1.getClippedCoordinates()[2], clipper1.getClippedCoordinates()[3]);
        if(!clipper2.isRejected())
            g2d.drawLine(clipper2.getClippedCoordinates()[0], clipper2.getClippedCoordinates()[1], clipper2.getClippedCoordinates()[2], clipper2.getClippedCoordinates()[3]);
        if(!clipper3.isRejected())
            g2d.drawLine(clipper3.getClippedCoordinates()[0], clipper3.getClippedCoordinates()[1], clipper3.getClippedCoordinates()[2], clipper3.getClippedCoordinates()[3]);
    }
    
    public boolean isClipped(){
        return clipped;
    }
    
    public boolean isRejected(){
        return rejected;
    }
}
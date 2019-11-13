/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dreflection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;

/**
 *
 * @author asus
 */
public class Triangle {
    
    Point p1 = new Point();
    Point p2 = new Point();
    Point p3 = new Point();
     
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3){   
        p1.setLocation(x1, y1);
        p2.setLocation(x2, y2);
        p3.setLocation(x3, y3);
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        g2d.drawLine(p2.x, p2.y, p3.x, p3.y);
        g2d.drawLine(p1.x, p1.y, p3.x, p3.y);
    } 
}

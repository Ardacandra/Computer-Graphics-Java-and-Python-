/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dclipping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Ardacandra Subiantoro(18/427572/PA/18532)
 */
public class ReflectionLine {
    private Point p1 = new Point();
    private Point p2 = new Point();
        
    public ReflectionLine(int x1, int y1, int x2, int y2) {
        p1.setLocation(x1, y1);
        p2.setLocation(x2, y2);
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}

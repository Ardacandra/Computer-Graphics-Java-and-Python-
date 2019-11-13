/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Ardacandra Subiantoro (18/427572/PA/18532)
 */
public class quadraticCurve extends JPanel{
    
    double XwMin = -2;
    double XwMax = 2;
    double YwMin = 0;
    double YwMax = 4;
    
    double XvMin = 100;
    double XvMax = 500;
    double YvMin = 100;
    double YvMax = 500;
            
    quadraticCurve() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawViewport(g);
    }
    
    void drawViewport(Graphics g){
        drawCartesian(g);
        drawCurve(g);
    }
    
    void drawCartesian(Graphics g){
        g.drawString(Integer.toString((int)XwMin), transformX((int)XwMin), transformY(0));
        for(int i=(int)XwMin ; i<(int)XwMax ; i++){
            g.drawLine( 
                   transformX(i),
                   transformY(0),
                   transformX(i+1),
                   transformY(0)
            );
            g.drawString(Integer.toString(i+1), transformX(i+1), transformY(0));
        }
        
        for(int i=(int)YwMin ; i<(int)YwMax ; i++){
            g.drawLine(
                    transformX(0),
                    transformY(i),
                    transformX(0),
                    transformY(i+1)
            );
            g.drawString(Integer.toString(i+1), transformX(0), transformY(i+1));
        }
    }
    
    void drawCurve(Graphics g){
        double x = XwMin;
        double y = YwMax;
        //bagian curve ketika gradient <= -1
        while(Math.abs(2*x) >= 1){
            double nextY = y-0.001;
            double nextX = Math.sqrt(y) * -1;
            g.drawLine(transformX(x), transformY(y), transformX(nextX), transformY(nextY));
            y = nextY;
            x = nextX;
        }
        //bagian curve ketika gradient >-1 dan <1
        while(Math.abs(2*x) < 1){
            double nextX = x+0.001;
            double nextY = Math.pow(x, 2);
            g.drawLine(transformX(x), transformY(y), transformX(nextX), transformY(nextY));
            y = nextY;
            x = nextX;
        }
        //bagian curve ketika gradient >= 1
        while(y <= YwMax){
            double nextY = y+0.001;
            double nextX = Math.sqrt(y);
            g.drawLine(transformX(x), transformY(y), transformX(nextX), transformY(nextY));
            y = nextY;
            x = nextX;          
        }
    }
    
    int transformX(double Xw){
        return (int)((Xw-XwMin)/(XwMax - XwMin)*(XvMax-XvMin)+XvMin);
    }
    
    int transformY(double Yw){
        return (int)(YvMax - ((Yw-YwMin)/(YwMax-YwMin)*(YvMax-YvMin)));
    }
}

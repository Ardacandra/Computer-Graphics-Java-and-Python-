/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dreflection;

/**
 *
 * @author asus
 */
public class Matrix {
    private double[] mtx = new double[2];
    
    public Matrix(double x, double y){
        mtx[0] = x;
        mtx[1] = y;
    }
    
    public double getX() {
        return mtx[0];
    }
    
    public double getY() {
        return mtx[1];
    }
    
    public Matrix translate(double tx, double ty){
        return new Matrix(
                tx + mtx[0],
                ty + mtx[1]
        );
    }
    
    public Matrix rotate(boolean clockwise, double dx, double dy) {
        
        double angle = Math.atan(dy/dx);
        if(clockwise){
            return new Matrix(
                    Math.cos(-angle)*mtx[0] - Math.sin(-angle)*mtx[1],
                    Math.sin(-angle)*mtx[0] + Math.cos(-angle)*mtx[1]
            );
        } else {
            return new Matrix(
                    Math.cos(angle)*mtx[0] - Math.sin(angle)*mtx[1],
                    Math.sin(angle)*mtx[0] + Math.cos(angle)*mtx[1]
            );
        }
    }
    
    public Matrix scale(double sx, double sy) {
        return new Matrix(
                sx * mtx[0],
                sy * mtx[1]
        );
    }
}

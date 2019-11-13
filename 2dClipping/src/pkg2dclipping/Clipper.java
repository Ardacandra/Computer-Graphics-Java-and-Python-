/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dclipping;

/**
 *
 * @author asus
 */
public class Clipper {
    private boolean clipped = false;
    private boolean rejected = false;
    private int x1, y1, x2, y2, xMin, xMax, yMin, yMax;
    private int[] clippedCoordinates;
    //Menggunakan Cohen-Sutherland Algorithm
    private int INSIDE = 0; //0000
    private int LEFT = 1;   //0001
    private int RIGHT = 2;  //0010
    private int BOTTOM = 4; //0100
    private int TOP = 8;    //1000
    
    
    public Clipper(int x1, int y1, int x2, int y2, int xMin, int xMax, int yMin, int yMax) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        setClippedCoordinates();
    }
    
    public boolean isClipped(){
        return clipped;
    }
    
    public boolean isRejected(){
        return rejected;
    }
    
    private void setClippedCoordinates(){
        clippedCoordinates = new int[4];
        int bitcode1 = getBitcode(x1, y1);
        int bitcode2 = getBitcode(x2, y2);
        
        boolean accept = false;
        
        while(true) {
            if((bitcode1 == 0) && (bitcode2 == 0)){
                //kedua titik ada dalam viewport;
                clippedCoordinates[0] = x1;
                clippedCoordinates[1] = y1;
                clippedCoordinates[2] = x2;
                clippedCoordinates[3] = y2;
                break;
            } else if ((bitcode1 & bitcode2) != 0){
                //kedua titik dalam satu sisi, di luar viewport
                rejected = true;
                break;
            } else {
                //ada bagian garis di dalam viewport
                int bitcode_luar;
                double x = 0;
                double y = 0;
                //memilih titik yang ada di luar 
                if(bitcode1 != 0)
                    bitcode_luar = bitcode1;
                else
                    bitcode_luar = bitcode2;               
                //mencari titik interseksi
                if((bitcode_luar & TOP) != 0){
                    //titik ada di atas
                    x = x1 + (x2 - x1) * (yMax - y1) / (y2 - y1);
                    y = yMax;
                } else if ((bitcode_luar & BOTTOM) != 0){
                    //titik ada di bawah
                    x = x1 + (x2 - x1) * (yMin - y1) / (y2 - y1);
                    y = yMin;                    
                } else if ((bitcode_luar & RIGHT) != 0){
                    //titik ada di kanan
                    y = y1 + (y2 - y1) * (xMax - x1) / (x2 - x1);
                    x = xMax;
                } else if ((bitcode_luar & LEFT) != 0){
                    //titik ada di kiri
                    y = y1 + (y2 - y1) * (xMin - x1) / (x2 - x1);
                    x = xMin;
                }
                //titik di luar viewport diganti titik interseksi
                if(bitcode_luar == bitcode1){
                    x1 = (int)x;
                    y1 = (int)y;
                    bitcode1 = getBitcode(x1, y1);
                } else {
                    x2 = (int)x;
                    y2 = (int)y;
                    bitcode2 = getBitcode(x2, y2);
                }
                clipped = true;
            }
        }
    }
    
    public int[] getClippedCoordinates() {
        return clippedCoordinates;
    }
    
    private int getBitcode(double x, double y) {
        int bitcode = INSIDE;
        
        if(x < xMin)
            bitcode |= LEFT;
        else if(x > xMax)
            bitcode |= RIGHT;
        if(y < yMin)
            bitcode |= BOTTOM;
        else if(y > yMax)
            bitcode |= TOP;        
        return bitcode;
    }
}

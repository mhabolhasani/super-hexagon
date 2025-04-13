package Game;

import java.awt.*;

public class Mahlar {
    private int xTopPoint;
    private int yTopPoint;
    private double Angle;

    public Mahlar(double Angle){
        this.Angle = Angle;
    }

    public void setAngle(double angle) {
        Angle = angle;
    }

    public double getAngle() {
        return Angle;
    }

    public int getxTopPoint() {
        return xTopPoint;
    }

    public int getyTopPoint() {
        return yTopPoint;
    }

    public int[] getXcoordinates(double theta) {
        int[] x = new int[3];
        int centerX = GamePanel.getCenterX();
        x[0] = (int) (centerX + 50 * Math.cos(theta + Math.PI / 6 - 0.15));
        x[1] = (int) (centerX + 50 * Math.cos(theta + Math.PI / 6 + 0.15));
        x[2] = (int) (centerX + 60 * Math.cos(theta + Math.PI / 6));
        xTopPoint = x[2];
        return x;
    }

    public int[] getYcoordinates(double theta) {
        int centerY = GamePanel.getCenterY();
        int[] y = new int[3];
        y[0] = (int) (centerY + 50 * Math.sin(theta + Math.PI / 6 - 0.150));
        y[1] = (int) (centerY + 50 * Math.sin(theta + Math.PI / 6  + 0.15));
        y[2] = (int) (centerY + 60 * Math.sin(theta + Math.PI / 6));
        yTopPoint = y[2];
        return y;
    }


    public void drawMahlar(Graphics2D g){
        int[] xCoordinates = getXcoordinates(this.Angle);
        int[] yCoordinates = getYcoordinates(this.Angle);
        g.fillPolygon(xCoordinates , yCoordinates , 3);
    }
}
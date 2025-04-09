package Game;

import javax.swing.*;
import java.awt.*;

public class obstacle {
    private int thinkness;
    public double angle;
    private int distanseFromCenter = 400;
    protected boolean isFinished;

    public obstacle(double angle){
        setAngle(angle);
        setThinkness(30);
        this.isFinished = false;
    }

    public int getThinkness() {
        return thinkness;
    }

    public void setThinkness(int thinkness) {
        this.thinkness = thinkness;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getDistanseFromCenter() {
        return this.distanseFromCenter;
    }

    public void setDistanseFromCenter(int distanseFromCenter) {
        this.distanseFromCenter = distanseFromCenter;
    }

    public int[] getxDimension(int centerX){
        int[] xCoordinates = new int[4];
        xCoordinates[0] = (int)( centerX + (this.distanseFromCenter * Math.cos(this.angle)));
        xCoordinates[1] = (int)( centerX + ((this.distanseFromCenter + this.thinkness) * Math.cos(this.angle)));
        xCoordinates[3] = (int)( centerX + (this.distanseFromCenter * Math.cos(this.angle + (Math.PI / 3))));
        xCoordinates[2] = (int)( centerX + ((this.distanseFromCenter + this.thinkness) * Math.cos(this.angle + (Math.PI / 3))));
        return xCoordinates;
    }

    public int[] getyDimension(int centerY){
        int[] yCoordinates = new int[4];
        yCoordinates[0] = (int) (centerY + (distanseFromCenter * Math.sin(angle)));
        yCoordinates[1] = (int) (centerY + ((distanseFromCenter + thinkness) * Math.sin(angle)));
        yCoordinates[3] = (int) (centerY + (distanseFromCenter * Math.sin(angle + (Math.PI / 3))));
        yCoordinates[2] = (int) (centerY + ((distanseFromCenter + thinkness) * Math.sin(angle + (Math.PI / 3))));
        return yCoordinates;
    }

    public void draw(Graphics g , int centerX , int centerY){
        if(!this.isFinished) {
            Graphics2D g2D = (Graphics2D) g;
            int[] xCoordinates = getxDimension(centerX);
            int[] yCoordinates = getyDimension(centerY);
            g.setColor(Color.BLACK);
            g.fillPolygon(xCoordinates, yCoordinates, 4);
            this.update();
        }
    }

    public void update(){
        this.distanseFromCenter -= 5;
        if(this.distanseFromCenter <= 50){
            this.isFinished = true;
        }
    }

}

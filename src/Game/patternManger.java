package Game;

import java.awt.*;

public class patternManger {
    private Graphics2D graphics2D;
    protected static obstaclePatterns obsPattern;
    public patternManger(Graphics2D g2D, double Angle, int centerX , int centerY){
        this.graphics2D = g2D;
        if(this.obsPattern == null){
            obsPattern = new obstaclePatterns(Angle);
        }
        if(this.obsPattern.isFinished){
            obsPattern = new obstaclePatterns(Angle);
        }
        this.obsPattern.drawObstacles(g2D,Angle,centerX,centerY);
    }
}

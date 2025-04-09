package Game;

import java.util.ArrayList;

public class Collision {
    public static boolean checkCollision(Mahlar mahlar, ArrayList<obstacle> obstacles, int centerX, int centerY) {
        for(obstacle obs : obstacles){
            if(obs.getDistanseFromCenter() < 80 && obs.getDistanseFromCenter() > 50) {
                int[] xPoints = obs.getxDimension(centerX);
                int[] yPoints = obs.getyDimension(centerY);
                if(xPoints[3] == xPoints[0]){
                    if (Math.abs(xPoints[1] - mahlar.getyTopPoint()) <= 1) {
                        return true;
                    }
                }else {
                    double m = (yPoints[3] - yPoints[1]) / (xPoints[3] - xPoints[0]);
                    double b = ((yPoints[0] * xPoints[3]) - (yPoints[3] * xPoints[0])) / (xPoints[3] - xPoints[0]);
                    double y = getLine(m, b, mahlar.getxTopPoint());
                    if (Math.abs(y - mahlar.getyTopPoint()) <= 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static double getLine(double m , double b , double x){
        return (m * x) + b;
    }
}

package Game;

import java.util.ArrayList;

public class Collision {
    public static boolean checkCollision(Mahlar mahlar, ArrayList<obstacle> obstacles, int centerX, int centerY) {
        //System.out.println("mahlar angle : " + mahlar.getAngle());
        for (obstacle obs : obstacles) {
            //System.out.println("obs abgle : " + obs.getAngle());
            if (obs.getDistanseFromCenter() < 100 && obs.getDistanseFromCenter() > 50) {
                //if(mahlar.getAngle() + Math.PI / 6 < obs.getAngle() && mahlar.getAngle() + Math.PI / 6 > obs.getAngle() - Math.PI / 3){
                //   System.out.println("in area of obstacle");
                //}
                if (Math.abs(obs.getAngle() - mahlar.getAngle()) <= 0.00001) {
                    if (obs.getDistanseFromCenter() < 65) {

                    }
                }
                int[] xPoints = obs.getxDimension(centerX);
                int[] yPoints = obs.getyDimension(centerY);
                if (xPoints[3] == xPoints[0]) {
                    if (Math.abs(xPoints[1] - mahlar.getyTopPoint()) <= 1) {
                        System.out.println(mahlar.getxTopPoint());
                        System.out.println(mahlar.getyTopPoint());
                        return true;
                    }
                } else {
                    double m = (yPoints[3] - yPoints[1]) / (xPoints[3] - xPoints[0]);
                    double b = ((yPoints[0] * xPoints[3]) - (yPoints[3] * xPoints[0])) / (xPoints[3] - xPoints[0]);
                    double y = getLine(m, b, mahlar.getxTopPoint());
                    if (Math.abs(y - mahlar.getyTopPoint()) <= 10) {
                        System.out.println(mahlar.getxTopPoint());
                        System.out.println(mahlar.getyTopPoint());
                        return true;
                    }
                }
            }
        }
        //System.out.println("--------------------------------------------");
        return false;
    }

    public static double getLine(double m, double b, double x) {
        return (m * x) + b;
    }

    public static boolean col(Mahlar mahlar, ArrayList<obstacle> obstacles, int centerX, int centerY) {
        for (obstacle obs : obstacles) {
            int dist = obs.getDistanseFromCenter();
            if (dist > 50 && dist < 100) {
                double obsAngle = normalize(obs.getAngle());
                double mahlarAngle = normalize(mahlar.getAngle());

                double diff = Math.abs(obsAngle - mahlarAngle);
                if (diff < 0.05 || diff > (2 * Math.PI - 0.05)) {
                    if (dist < 65){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static double normalize(double angle) {
        return (angle % (2 * Math.PI) + 2 * Math.PI) % (2 * Math.PI);
    }
}

package Game;

import java.util.ArrayList;

public class Collision {
    public static boolean checkCollision(Mahlar mahlar, ArrayList<obstacle> obstacles, int centerX, int centerY) {
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

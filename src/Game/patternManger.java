package Game;

import java.awt.*;
import java.util.ArrayList;

public class patternManger {
    private Graphics2D graphics2D;
    protected static ArrayList<obstaclePatterns> patternList = new ArrayList<>();
    public patternManger(Graphics2D g2D, double Angle){
        this.graphics2D = g2D;
        if(patternList.isEmpty()){
            patternList.add(new obstaclePatterns(Angle));
        }
        if(patternList.get(patternList.size() - 1).mainPattern.get(0).getDistanseFromCenter() == 250){
            patternList.add(new obstaclePatterns(Angle));
        }

        for(int i = 0; i < patternList.size() - 1 ; i++){
            if(patternList.get(i).isFinished){
                patternList.remove(i);
            }
        }

        for(obstaclePatterns obs : patternList){
            obs.drawObstacles(g2D, Angle);
        }
    }
}

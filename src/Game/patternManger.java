package Game;

import java.awt.*;
import java.util.ArrayList;

public class patternManger {
    private Graphics2D graphics2D;
    protected static ArrayList<obstaclePatterns> patternList = new ArrayList<>();
    private static float speed = (float) 2.5;

    public patternManger(Graphics2D g2D, double Angle){
        this.graphics2D = g2D;
        if(patternList.isEmpty()){
            patternList.add(new obstaclePatterns(Angle , speed));
            speed += 0.07;
        }
        if(patternList.get(patternList.size() - 1).mainPattern.get(0).getDistanseFromCenter() >= 250
         && patternList.get(patternList.size() - 1).mainPattern.get(0).getDistanseFromCenter() <= 255){
            patternList.add(new obstaclePatterns(Angle , speed));
            speed += 0.07;
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

    public void update(){
        this.patternList = new ArrayList<>();
        this.speed = (float) 2.5;
    }
}
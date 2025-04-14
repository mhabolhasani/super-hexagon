package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class obstaclePatterns {
    private ArrayList<obstacle> patternType1 ;
    private ArrayList<obstacle> patternType2;
    private ArrayList<obstacle> patternType3;
    protected ArrayList<obstacle> mainPattern = new ArrayList<>();
    private ArrayList<Integer> ignoredPlaces = new ArrayList<>();
    private Random random = new Random();
    private double initialAngle;
    protected boolean isFinished;
    private float speed;
    public void setInitialAngle(double initialAngle) {
        this.initialAngle = initialAngle;
    }

    public obstaclePatterns(double theta , float speed) {
        this.speed = speed;
        this.initialAngle = theta;
        this.isFinished = false;
        int type = this.random.nextInt(3) + 1;
        switch(type){
            case 1:
                this.setPatternType1();
                updateMainPatterns();
                break;
            case 2:
                this.setPatternType2();
                updateMainPatterns();
                break;
            case 3:
                this.setPatternType3();
                updateMainPatterns();
                break;
        }
    }

    private void setPatternType1(){
        patternType1 = new ArrayList<>();
        int ignored = random.nextInt(6);
        ArrayList<Integer> ignored1 = new ArrayList<>();
        ignored1.add(ignored);
        ignoredPlaces = ignored1;
    }

    private void setPatternType2(){
        patternType2 = new ArrayList<>();
        ArrayList<Integer> IG = new ArrayList<>();
        IG.add(0);
        IG.add(2);
        IG.add(4);
        ignoredPlaces = IG;
    }

    private void setPatternType3(){
        patternType3 = new ArrayList<>();
        int numberOfIgnored = random.nextInt(5) + 1;
        ArrayList<Integer> IG = new ArrayList<>();
        for(int i = 0;i < numberOfIgnored ; i++){
            IG.add(random.nextInt(6));
        }
        ignoredPlaces = IG;
    }

    public void updateMainPatterns(){
        for(int i = 0 ; i < 6 ; i++){
            if(!ignoredPlaces.contains(i)){
                mainPattern.add(new obstacle(this.initialAngle + i * Math.PI / 3));
            }
        }
    }

    public void updateObstacles(){
        int index = 0;
        for(int i = 0; i < 6; i++){
            if(!ignoredPlaces.contains(i)){
                mainPattern.get(index).setAngle(this.initialAngle + i * Math.PI / 3);
                index++;
            }
        }
    }

    public void drawObstacles(Graphics2D g2D, double Angle) {
        this.initialAngle = Angle;
        updateObstacles();
        if (!this.isFinished){
            for (obstacle Obs : mainPattern) {
                Obs.draw(g2D , speed);
            }
            this.updateFinished(mainPattern);
        }
    }

    public void updateFinished(ArrayList<obstacle> obs) {
        this.isFinished = obs.get(0).isFinished;
    }

}
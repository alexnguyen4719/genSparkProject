package com.example.dragonadventure;

import java.util.Random;

public class GamePlay {
    public boolean gameState;
    public boolean result;
    public boolean startDisplayResult;
    public boolean moveToHome;
    public boolean drawChest;
    public boolean openCage;
    public int dir;
    private Random random;
    public GamePlay(){
        random = new Random();
    }

    public boolean ChooseDragon(){
        return random.nextBoolean();
    }

    public void setTargetPosition(int po){
        openCage = true;
        moveToHome = true;
        dir = ((po == 2)?1:-1);
        startDisplayResult = false;
        drawChest = false;
        this.result = ChooseDragon();
    }
}

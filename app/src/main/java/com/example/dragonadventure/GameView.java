package com.example.dragonadventure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameView extends View {
    Context context;
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS = 30;
    Bitmap background;
    Display display;
    Point point;
    int dWidth;
    int dHeight;
    int playerFrame;
    int maxPlayerFrame;
    int x,y;
    private final int xVelocity = 3;
    private final int yVelocity = 10;
    public int targetX, targetY;
    private final float speedFrame = 0.3f;
    private final int posDragonLeftX = 150, posDragonRightX = -150, posDragonY = 200;
    private final int posCageLeftX = 200, posCageRightX = -200, posCageY = 250;
    private final int posChestLeftX = 150, posChestRightX = -150, posChestY = 350;
    private float timeFrame;
    Rect rect;
    CharacterSprites playerSprite;
    ButtonView button1;
    ButtonView button2;
    ButtonView button0;
    GamePlay gamePlay;
    CageSprite cage;
    DragonSprite dragonSprite;
    TreasureSprite chestSprite;

    public GameView(Context context) {
        super(context);
        this.context = context;
        handler = new Handler();
        runnable = new Runnable(){
            @Override
            public void run(){
                invalidate();
            }
        } ;
        gamePlay = new GamePlay();
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0,0,dWidth, dHeight);
        cage = new CageSprite(this);
        dragonSprite = new DragonSprite(this);
        chestSprite = new TreasureSprite(this);
        playerSprite = new CharacterSprites(this, point, gamePlay);
        x = point.x / 2 - playerSprite.getWidth() / 2;
        y = point.y / 2 - playerSprite.getHeight() / 2;
        button0 = new ButtonView(200, (int)(528f/860f*200f), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.quit), 200, (int)(528f/860f*200f), true));
        button0.setPosition(dWidth/2 - 100, dHeight - (int)(528f/860f*200f)*2);
        button1 = new ButtonView(200, (int)(324f/920f*200f), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.blue_angle), 200, (int)(324f/920f*200f), true));
        button1.setPosition(dWidth/2 - 350, dHeight - (int)(324f/920f*200f)*5);
        button2 = new ButtonView(200, (int)(528f/860f*200f), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.blue_angle), 200, (int)(324f/920f*200f), true));
        button2.setPosition(dWidth/2 + 150, dHeight - (int)(324f/920f*200f)*5);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(background,null,rect,null);
        button0.draw(canvas);
        button1.draw(canvas);
        button2.draw(canvas);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(!gamePlay.gameState) {
            maxPlayerFrame = 5;
            gamePlay.openCage = false;
            if (playerFrame > maxPlayerFrame) playerFrame = 0;
            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), -dragonSprite.getWidth() / 2 + posDragonLeftX * point.x / 768, -dragonSprite.getHeight() / 2 + posDragonY * point.y / 1248, null);
            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), point.x - dragonSprite.getWidth() / 2 + posDragonRightX * point.x / 768, -dragonSprite.getHeight() / 2 + posDragonY * point.y / 1248, null);
            canvas.drawBitmap(playerSprite.GetPlayerBitMap(0, playerFrame), x, y, null);
            canvas.drawBitmap(cage.cage, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
            canvas.drawBitmap(cage.cage, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
        }else{
            maxPlayerFrame = 7;
            if(playerFrame > maxPlayerFrame) playerFrame = 0;
//            if(gamePlay.moveToHome){
//                if (gamePlay.dir == -1) {
//                    canvas.drawBitmap(cage.cageOpen, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                    canvas.drawBitmap(cage.cage, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                } else {
//                    canvas.drawBitmap(cage.cageOpen, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                    canvas.drawBitmap(cage.cage, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                }
//            }
//            else{
//                if (gamePlay.dir == 1) {
//                    canvas.drawBitmap(cage.cageOpen, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                    canvas.drawBitmap(cage.cage, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                } else {
//                    canvas.drawBitmap(cage.cageOpen, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                    canvas.drawBitmap(cage.cage, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
//                }
//            }

            if(Math.abs(targetY - y) <= 5){
                if(!gamePlay.startDisplayResult){
                    gamePlay.startDisplayResult = true;
                    playerFrame = 0;
                }else{
                    if(!gamePlay.moveToHome){
                        gamePlay.moveToHome = true;
                        gamePlay.gameState = false;
                        gamePlay.drawChest = false;
                        return;
                    }
                    if(gamePlay.result){
                        if(gamePlay.dir == -1) {
                            canvas.drawBitmap(cage.cageOpen, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                            if(playerFrame > 4 && !gamePlay.drawChest) {
                                canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), -dragonSprite.GetDragonIdle(playerFrame).getWidth() / 2 + posDragonLeftX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                            }else{
                                gamePlay.drawChest = true;
                            }
                            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), point.x - dragonSprite.GetDragonIdle(playerFrame).getWidth()/2 + posDragonRightX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                            canvas.drawBitmap(cage.cage, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                        }else{
                            canvas.drawBitmap(cage.cageOpen, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                            if(playerFrame > 4 && !gamePlay.drawChest) {
                                canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), point.x - dragonSprite.getWidth() / 2 + posDragonRightX * point.x / 768, -dragonSprite.getHeight() / 2 + posDragonY * point.y / 1248, null);
                            }else{
                                gamePlay.drawChest = true;
                            }
                            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), - dragonSprite.GetDragonIdle(playerFrame).getWidth()/2 + posDragonLeftX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                            canvas.drawBitmap(cage.cage, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                        }
                        canvas.drawBitmap(playerSprite.GetPlayerBitMap(5, playerFrame), x, y, null);
                    }else{
                        if(gamePlay.dir == -1) {
                            canvas.drawBitmap(cage.cageOpen, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                            canvas.drawBitmap(playerSprite.GetPlayerBitMap(4, playerFrame), x, y, null);
                            canvas.drawBitmap(dragonSprite.GetDragonAttack(playerFrame, gamePlay.dir), -dragonSprite.GetDragonIdle(playerFrame).getWidth() / 2 + posDragonLeftX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), point.x - dragonSprite.GetDragonIdle(playerFrame).getWidth()/2 + posDragonRightX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                            canvas.drawBitmap(cage.cage, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                        }else{
                            canvas.drawBitmap(cage.cageOpen, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                            canvas.drawBitmap(playerSprite.GetPlayerBitMap(3, playerFrame), x, y, null);
                            canvas.drawBitmap(dragonSprite.GetDragonAttack(playerFrame, gamePlay.dir),  point.x - dragonSprite.GetDragonIdle(playerFrame).getWidth() / 2 + posDragonRightX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), - dragonSprite.GetDragonIdle(playerFrame).getWidth()/2 + posDragonLeftX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                            canvas.drawBitmap(cage.cage, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                        }
                    }
                }
                if(playerFrame == maxPlayerFrame) {
                    gamePlay.moveToHome = false;
                    gamePlay.dir = -gamePlay.dir;
                    targetX = point.x / 2 - playerSprite.getWidth() / 2;
                    targetY = point.y / 2 - playerSprite.getHeight() / 2;
                    CharSequence text;
                    if (gamePlay.result) {
                        text = "Congratulation!!";
                    } else {
                        text = "Good luck!!";
                    }
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }else {
                maxPlayerFrame = 5;
                if(playerFrame > maxPlayerFrame) playerFrame = 0;

                if(!gamePlay.moveToHome){
                    if(gamePlay.dir == -1) {
                        canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), - dragonSprite.GetDragonIdle(playerFrame).getWidth()/2 + posDragonLeftX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                        if(!gamePlay.result) {
                            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), point.x - dragonSprite.GetDragonIdle(playerFrame).getWidth() / 2 + posDragonRightX*point.x/768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY * point.y / 1248, null);
                        }
                    }else{
                        if(!gamePlay.result) {
                            canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), -dragonSprite.GetDragonIdle(playerFrame).getWidth() / 2 + posDragonLeftX * point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY * point.y / 1248, null);
                        }
                        canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), point.x - dragonSprite.GetDragonIdle(playerFrame).getWidth()/2 + posDragonRightX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                    }
                }else{
                    canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), -dragonSprite.GetDragonIdle(playerFrame).getWidth() / 2 + posDragonLeftX * point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY * point.y / 1248, null);
                    canvas.drawBitmap(dragonSprite.GetDragonIdle(playerFrame), point.x - dragonSprite.GetDragonIdle(playerFrame).getWidth()/2 + posDragonRightX*point.x / 768, -dragonSprite.GetDragonIdle(playerFrame).getHeight() / 2 + posDragonY*point.y/1248, null);
                }
                canvas.drawBitmap(cage.cage, -cage.getWidth() / 2 + posCageLeftX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                canvas.drawBitmap(cage.cage, point.x - cage.getWidth() / 2 + posCageRightX * point.x / 768, -cage.getHeight() / 2 + posCageY * point.y / 1248, null);
                x += xVelocity * gamePlay.dir;
                if(gamePlay.moveToHome) {
                    y -= yVelocity;
                }else{
                    y += yVelocity;
                }
                if (gamePlay.dir == 1) {
                    canvas.drawBitmap(playerSprite.GetPlayerBitMap(1, playerFrame), x, y, null);
                } else {
                    canvas.drawBitmap(playerSprite.GetPlayerBitMap(2, playerFrame), x, y, null);
                }
            }
        }
        if(gamePlay.drawChest && gamePlay.moveToHome){
            if(gamePlay.dir == 1){
                canvas.drawBitmap(chestSprite.chestSprite, point.x - chestSprite.chestSprite.getWidth() / 2 + posChestRightX*point.x / 768, -chestSprite.chestSprite.getHeight() / 2 + posChestY*point.y/1248, null);
            }else{
                canvas.drawBitmap(chestSprite.chestSprite,  - chestSprite.chestSprite.getWidth() / 2 + posChestLeftX*point.x / 768, -chestSprite.chestSprite.getHeight() / 2 + posChestY*point.y/1248, null);
            }
        }
        if(timeFrame > 1) {
            playerFrame++;
            timeFrame = 0;
        }else{
            timeFrame += speedFrame;
        }
        if(playerFrame > maxPlayerFrame) playerFrame = 0;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        if(button0.btn_rect.contains(x,y)){
            System.exit(0);
        }else if(button1.btn_rect.contains(x,y)){
            if(!gamePlay.gameState){
                gamePlay.gameState = true;
                gamePlay.setTargetPosition(1);
            }
        }else if(button2.btn_rect.contains(x,y)){
            if(!gamePlay.gameState){
                gamePlay.gameState = true;
                gamePlay.setTargetPosition(2);
            }
        }
        this.targetX = - playerSprite.getWidth()/2 - posDragonRightX * gamePlay.dir*point.x / 768;
        this.targetY = -playerSprite.getHeight() / 2 + posDragonY*point.y/1248;
        return true;
    }
}

package com.example.dragonadventure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;

public class CharacterSprites {
    Context context;
    Bitmap[] playerIdle;
    Bitmap[] playerMoveRight;
    Bitmap[] playerMoveLeft;
    Bitmap[] playerDie;
    Bitmap[] playerWin;
    Bitmap[] playerAttack;
    Bitmap[] playerDieLeft;

    final float characterSize = 0.765f;

    public CharacterSprites(View views, Point size, GamePlay gamePlay) {
        this.context = views.getContext();
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        playerIdle = new Bitmap[6];
        playerIdle[0] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_idle_01), characterSize, characterSize);
        playerIdle[1] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_idle_02), characterSize, characterSize);
        playerIdle[2] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_idle_03), characterSize, characterSize);
        playerIdle[3] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_idle_04), characterSize, characterSize);
        playerIdle[4] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_idle_05), characterSize, characterSize);
        playerIdle[5] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_idle_06), characterSize, characterSize);
        playerMoveRight = new Bitmap[6];
        playerMoveLeft = new Bitmap[6];
        playerMoveRight[0] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_walk_01), characterSize, characterSize);
        playerMoveLeft[0] = flip(playerMoveRight[0], m);
        playerMoveRight[1] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_walk_02), characterSize, characterSize);
        playerMoveLeft[1] = flip(playerMoveRight[1], m);
        playerMoveRight[2] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_walk_03), characterSize, characterSize);
        playerMoveLeft[2] = flip(playerMoveRight[2], m);
        playerMoveRight[3] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_walk_04), characterSize, characterSize);
        playerMoveLeft[3] = flip(playerMoveRight[3], m);
        playerMoveRight[4] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_walk_05), characterSize, characterSize);
        playerMoveLeft[4] = flip(playerMoveRight[4], m);
        playerMoveRight[5] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_walk_06), characterSize, characterSize);
        playerMoveLeft[5] = flip(playerMoveRight[5], m);
        playerDie = new Bitmap[8];
        playerDieLeft = new Bitmap[8];
        playerDie[0] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_01), characterSize, characterSize);
        playerDieLeft[0] = flip(playerDie[0], m);
        playerDie[1] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_02), characterSize, characterSize);
        playerDieLeft[1] = flip(playerDie[1], m);
        playerDie[2] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_03), characterSize, characterSize);
        playerDieLeft[2] = flip(playerDie[2], m);
        playerDie[3] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_04), characterSize, characterSize);
        playerDieLeft[3] = flip(playerDie[3], m);
        playerDie[4] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_05), characterSize, characterSize);
        playerDieLeft[4] = flip(playerDie[4], m);
        playerDie[5] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_06), characterSize, characterSize);
        playerDieLeft[5] = flip(playerDie[5], m);
        playerDie[6] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_07), characterSize, characterSize);
        playerDieLeft[6] = flip(playerDie[6], m);
        playerDie[7] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_die_08), characterSize, characterSize);
        playerDieLeft[7] = flip(playerDie[7], m);
        playerWin = new Bitmap[8];
        playerWin[0] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_01), characterSize, characterSize);
        playerWin[1] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_02), characterSize, characterSize);
        playerWin[2] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_01), characterSize, characterSize);
        playerWin[3] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_02), characterSize, characterSize);
        playerWin[4] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_01), characterSize, characterSize);
        playerWin[5] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_02), characterSize, characterSize);
        playerWin[6] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_01), characterSize, characterSize);
        playerWin[7] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_win_02), characterSize, characterSize);
        playerAttack = new Bitmap[6];
        playerAttack[0] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_attack_01), characterSize, characterSize);
        playerAttack[1] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_attack_02), characterSize, characterSize);
        playerAttack[2] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_attack_03), characterSize, characterSize);
        playerAttack[3] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_attack_04), characterSize, characterSize);
        playerAttack[4] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_attack_05), characterSize, characterSize);
        playerAttack[5] = Resize(BitmapFactory.decodeResource(views.getResources(), R.drawable.knight_attack_06), characterSize, characterSize);
    }
    public Bitmap GetPlayerBitMap(int type, int frame){
        switch (type){
            case 0:
                return playerIdle[frame % 6];
            case 1:
                return playerMoveRight[frame % 6];
            case 2:
                return playerMoveLeft[frame % 6];
            case 3:
                return playerDie[frame % 8];
            case 4:
                return playerDieLeft[frame % 8];
            case 5:
                return playerWin[frame % 8];
            case 6:
                return playerAttack[frame % 6];
        }
        return null;
    }

    public Bitmap flip(Bitmap src, Matrix m)
    {
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }
    public Bitmap Resize(Bitmap bitmap, float newSizeX, float newSizeY){
        Matrix mx = new Matrix();
        mx.preScale(newSizeX, newSizeY);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mx, false);
    }

    public int getWidth(){
        return playerIdle[0].getWidth();
    }
    public int getHeight(){
        return playerIdle[0].getHeight();
    }
//    private void DrawResize(@NonNull Canvas canvas, Bitmap src, float posX, float posY, float newSizeX, float newSizeY){
//        Bitmap x = Resize(src, newSizeX, newSizeY);
//        canvas.drawBitmap(x, posX + src.getWidth() * (1-newSizeX)/2, posY + src.getHeight() * (1-newSizeY), null);
//    }
}
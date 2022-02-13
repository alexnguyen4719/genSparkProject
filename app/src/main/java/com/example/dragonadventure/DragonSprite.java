package com.example.dragonadventure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;

public class DragonSprite {
    private float dragonSize = 0.765f;
    private Bitmap[] dragonIdle;
    private Bitmap[] dragonAttack;
    private Bitmap[] dragonAttackRight;

    public DragonSprite(View views){
        dragonIdle = new Bitmap[6];
        dragonIdle[0] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.idle_01), dragonSize);
        dragonIdle[1] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.idle_02), dragonSize);
        dragonIdle[2] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.idle_03), dragonSize);
        dragonIdle[3] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.idle_04), dragonSize);
        dragonIdle[4] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.idle_05), dragonSize);
        dragonIdle[5] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.idle_06), dragonSize);
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        dragonAttack = new Bitmap[3];
        dragonAttackRight = new Bitmap[3];
        dragonAttack[0] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.attack_01), dragonSize);
        dragonAttackRight[0] = flip(dragonAttack[0], m);
        dragonAttack[1] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.attack_02), dragonSize);
        dragonAttackRight[1] = flip(dragonAttack[1], m);
        dragonAttack[2] = ResizeDragon(BitmapFactory.decodeResource(views.getResources(), R.drawable.attack_03), dragonSize);
        dragonAttackRight[2] = flip(dragonAttack[2], m);
    }
//    public Point GetDeclin(){
//        return new Point((int)(dragonIdle[0].getWidth() * (1f/dragonSize - 1f) / 2f), (int)(dragonIdle[0].getHeight() * (1f/dragonSize - 1f)));
//    }

    public Bitmap GetDragonIdle(int frame){
        return dragonIdle[frame % 6];
    }

    public Bitmap GetDragonAttack(int frame, int dir){
        if(dir == -1) {
            return dragonAttack[frame % 3];
        }else{
            return dragonAttackRight[frame % 3];
        }
    }

    Bitmap flip(Bitmap src, Matrix m)
    {
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }

    public Bitmap ResizeDragon(Bitmap bitmap, float newSize){
        Matrix mx = new Matrix();
        mx.preScale(newSize, newSize);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mx, false);
    }

    public int getWidth(){
        return dragonIdle[0].getWidth();
    }
    public int getHeight(){
        return dragonIdle[0].getHeight();
    }
}

package com.example.dragonadventure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.view.View;

public class CageSprite {
    public Bitmap cage;
    public Bitmap cageOpen;
    public Matrix m;
    final float cageSize = 0.20f;

    public CageSprite(View views){
        cage = BitmapFactory.decodeResource(views.getResources(), R.drawable.cage_close);
        cageOpen = BitmapFactory.decodeResource(views.getResources(), R.drawable.cage_open);
        m = new Matrix();
        m.preScale(cageSize, cageSize);
        cage = Bitmap.createBitmap(cage, 0, 0, cage.getWidth(), cage.getHeight(), m, false);
        cageOpen = Bitmap.createBitmap(cageOpen, 0, 0, cageOpen.getWidth(), cageOpen.getHeight(), m, false);
    }
    public int getWidth(){
        return cage.getWidth();
    }
    public int getHeight(){
        return cage.getHeight();
    }
//    public Point GetDeclin(){
//        return new Point((int)(cage.getWidth() * (1f/cageSize - 1f) / 2f), 0);
//    }
//    public Bitmap RotateCage(float angle){
//        Matrix mx = new Matrix();
//        mx.preRotate(angle);
//        return Bitmap.createBitmap(cage, 0, 0, cage.getWidth(), cage.getHeight(), mx, false);
//    }
}

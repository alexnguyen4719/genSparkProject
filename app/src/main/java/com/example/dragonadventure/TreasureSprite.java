package com.example.dragonadventure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;

public class TreasureSprite {
    public Bitmap chestSprite;

    public TreasureSprite(View views){
        chestSprite = BitmapFactory.decodeResource(views.getResources(), R.drawable.chest);
        Matrix m = new Matrix();
        m.preScale(5f, 5f);
        chestSprite = Bitmap.createBitmap(chestSprite, 0, 0, chestSprite.getWidth(), chestSprite.getHeight(), m, false);

    }
}

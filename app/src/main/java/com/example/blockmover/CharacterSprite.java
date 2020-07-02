package com.example.blockmover;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite
{
    private Bitmap image;
    private int x,y;
    private int xVelocity = 10;
    private int yVelocity = 10;

    public CharacterSprite(Bitmap bmp)
    {
        //Set Image source
        image = bmp;
        //Coordinate
        x = 100;
        y = 100;

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x,y,null);
        
    }

    public void update()
    {
        //Logic of CharacterSprite
        y += yVelocity;
    }
}

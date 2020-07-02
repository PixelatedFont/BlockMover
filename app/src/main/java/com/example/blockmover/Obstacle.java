package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject
{
    private Rect rectangle;
    private int color;

    public Obstacle(Rect rect, int color)
    {
        this.rectangle = rect;
        this.color = color;
    }

    public boolean playerCollide(PlayerObject player)
    {
        //if player touches object return true
        if (rectangle.contains(player.getRectangle().left, player.getRectangle().top)
            || rectangle.contains(player.getRectangle().right, player.getRectangle().top)
            || rectangle.contains(player.getRectangle().left, player.getRectangle().bottom)
            || rectangle.contains(player.getRectangle().right, player.getRectangle().bottom))
        {
            return true;
        }
        else return false;
    }

    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}

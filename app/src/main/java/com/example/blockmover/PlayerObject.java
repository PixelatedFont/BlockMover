package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class PlayerObject implements GameObject
{
    private Rect rectangle;
    private int color;

    public PlayerObject(Rect rect, int color)
    {
        this.rectangle = rect;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
    }

    @Override
    public void update()
    {

    }

    public void update(Point point)
    {
        //left top right bottom
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2,point.y + rectangle.height()/2);
    }
}

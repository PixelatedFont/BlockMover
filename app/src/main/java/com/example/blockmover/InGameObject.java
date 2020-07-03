package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class InGameObject implements GameObject
{
    private Rect rectangle;
    private int color;
    private boolean isTouchable = false;

    public InGameObject(Rect rect, int color, boolean isTouchable)
    {
        this.rectangle = rect;
        this.color = color;
        this.isTouchable = isTouchable;
    }

    public boolean playerCollide(PlayerObject player)
    {
        //if player touches object return true
        if (this.isTouchable)
        {
            return Rect.intersects(rectangle, player.getRectangle());
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
    public void update()
    {
    }

    public void update(Point point)
    {
        //left top right bottom
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2,point.y + rectangle.height()/2);
    }
}

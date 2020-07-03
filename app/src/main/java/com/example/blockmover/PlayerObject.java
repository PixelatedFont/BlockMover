package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Timer;

public class PlayerObject implements GameObject
{
    private Rect rectangle;
    private int color;
    MainUI manager;

    public Rect getRectangle() {
        return rectangle;
    }

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
        InputKey key = MainUI.getInstance().GetCurrentPress();
        if (MainUI.getInstance().getButton(0).isPressed() == false)
        {
            MainUI.getInstance().PressButton(InputKey.None);
        }

        if (key == InputKey.Right)
        {
            point.x += 50;
        }

        if (key == InputKey.Left)
        {
            point.x -= 50;
        }

        if (key == InputKey.Up)
        {
            point.y -= 50;
        }

        if (key == InputKey.Down)
        {
            point.y += 50;
        }
    }


}

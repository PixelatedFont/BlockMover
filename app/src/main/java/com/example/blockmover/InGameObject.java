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



    public Rect getRectangle()
    {
        return rectangle;
    }

    public InGameObject(Rect rect, int color, boolean isTouchable)
    {
        this.rectangle = rect;
        this.color = color;
        this.isTouchable = isTouchable;
    }

    public String playerCollide(PlayerObject player)
    {
        //if player touches object return true
        //System.out.println("Colliding");
        String collision;

        Rect oldPos = player.getRectangle();

        if (Rect.intersects(rectangle, player.getRectangle()))
        {
            if (Constants.xVelocity > 0)
            {
                return "right";
            }

            if (Constants.xVelocity < 0 )
            {
                return "left";
            }

            if (Constants.yVelocity > 0 )
            {
                return "bottom";
            }

            if (Constants.yVelocity < 0 )
            {
                return "top";
            }
        }





        return "null";


    }



    public boolean playerCollideTest(PlayerObject player)
    {
        //if player touches object return true
        if (this.isTouchable)
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
    public void update()
    {
    }

    public void update(Point point)
    {
        //left top right bottom
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2,point.y + rectangle.height()/2);

        //hitBox(this.rectangle);
    }

    public void setCoordinate(int PointX, int PointY)
    {
        //left top right bottom
        rectangle.set(PointX - rectangle.width()/2, PointY - rectangle.height()/2, PointX + rectangle.width()/2,PointY + rectangle.height()/2);

        //hitBox(this.rectangle);
    }

}

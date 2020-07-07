package com.example.blockmover;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class InGameObject implements GameObject
{
    private Rect rectangle;
    private int color;
    private boolean isTouchable = false;
    private boolean isMovable = false;
    private AnimationManager animationM;

    private Animation groundBlock;
    private Animation oneXBlock;
    private Animation exitPoint;
    private Animation borderBlock;



    public Rect getRectangle()
    {
        return rectangle;
    }

    public InGameObject(Rect rect, int color, boolean isTouchable, boolean isMovable)
    {
        this.rectangle = rect;
        this.color = color;
        this.isTouchable = isTouchable;
        this.isMovable = isMovable;

        BitmapFactory bitmapF = new BitmapFactory();
        Bitmap groundBlockImg = bitmapF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.groundblock);
        Bitmap oneXBlockImg = bitmapF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.onexblock);
        Bitmap borderBlockImg = bitmapF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.border);

        groundBlock = new Animation(new Bitmap[]{groundBlockImg},2);
        oneXBlock = new Animation(new Bitmap[]{oneXBlockImg},2);
        borderBlock = new Animation(new Bitmap[]{borderBlockImg}, 2);

        animationM = new AnimationManager(new Animation[]{groundBlock,oneXBlock, borderBlock});

    }

    public String playerCollide(PlayerObject player)
    {
        //if player touches object return true
        //System.out.println("Colliding");
        String collision;

        Rect oldPos = player.getRectangle();


        if (Rect.intersects(rectangle, player.getRectangle()))
        {
            if (isTouchable && isMovable )
            {
                if (Constants.xVelocity > 0) {
                    return "right";
                }

                if (Constants.xVelocity < 0) {
                    return "left";
                }

                if (Constants.yVelocity > 0) {
                    return "bottom";
                }

                if (Constants.yVelocity < 0) {
                    return "top";
                }
            }

            if (isTouchable & !isMovable)
            {
                return "0";
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
        //Paint paint = new Paint();
        //paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //paint.setColor(color);
        //canvas.drawRect(rectangle, paint);

        animationM.draw(canvas, rectangle);

    }

    @Override
    public void update()
    {
        animationM.update();
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

    public boolean isSolid()
    {

        return true;
    }

    public void setSprite(int index)
    {
        if (index  == 0)
        {
            animationM.playAnim(0);
        }

        if (index == 1)
        {
            animationM.playAnim(1);
        }

        if (index == 2)
        {
            animationM.playAnim(2);
        }

        animationM.update();
    }

}

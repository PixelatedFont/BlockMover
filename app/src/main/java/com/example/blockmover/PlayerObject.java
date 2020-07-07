package com.example.blockmover;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Timer;

public class PlayerObject implements GameObject
{
    private Rect rectangle;
    private int color;
    private String movement;
    MainUI manager;
    private AnimationManager animationM;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private Animation walkUp;
    private Animation walkDown;

    public boolean rightKeyPressed;

    public Rect getRectangle() {
        return rectangle;
    }

    public PlayerObject(Rect rect, int color)
    {
        this.rectangle = rect;
        this.color = color;

        BitmapFactory bitmapF = new BitmapFactory();
        Bitmap walkLeftImg = bitmapF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.main_left);
        Bitmap walkRightImg = bitmapF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.main_right);
        Bitmap walkUpImg = bitmapF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.main_up);
        Bitmap walkDownImg = bitmapF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.main_down);

        walkLeft = new Animation(new Bitmap[]{walkLeftImg},2);
        walkRight = new Animation(new Bitmap[]{walkRightImg},2);
        walkUp = new Animation(new Bitmap[]{walkUpImg},2);
        walkDown = new Animation(new Bitmap[]{walkDownImg},2);

        animationM = new AnimationManager(new Animation[]{walkLeft, walkUp, walkRight, walkDown});

        animationM.playAnim(0);
        animationM.update();




        /*Matrix matrix = new Matrix();
        matrix.preScale(-1,1);
        walkLeftImg = Bitmap.createBitmap(walkLeftImg,0,0, walkLeftImg.getWidth(), walkLeftImg.getHeight(),matrix,false);
        walkRightImg = Bitmap.createBitmap(walkRightImg,0,0, walkRightImg.getWidth(), walkRightImg.getHeight(),matrix,false);
        walkUpImg = Bitmap.createBitmap(walkUpImg,0,0, walkUpImg.getWidth(), walkUpImg.getHeight(),matrix,false);
        walkDownImg = Bitmap.createBitmap(walkDownImg,0,0, walkDownImg.getWidth(), walkDownImg.getHeight(),matrix,false);*/




    }

    @Override
    public void draw(Canvas canvas)
    {
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.translate(rectangle.centerX(),rectangle.centerY());
        //canvas.drawRect(rectangle,paint);

        animationM.draw(canvas, rectangle);

    }

    @Override
    public void update()
    {
        animationM.update();
    }

    public void setPoint(int pointX, int pointY)
    {
        rectangle.set(pointX - rectangle.width()/2, pointY - rectangle.height()/2, pointX + rectangle.width()/2,pointY + rectangle.height()/2);

    }


    public void update(Point point)
    {
        //left top right bottom
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2,point.y + rectangle.height()/2);
        InputKey key = MainUI.getInstance().GetCurrentPress();
        int state = 0;

        if (key == InputKey.Right)
        {
            animationM.playAnim(2);
            point.x = point.x + Constants.xVelocity;
            movement = "right";
            MainUI.getInstance().PressButton(InputKey.None);
        }

        if (key == InputKey.Left)
        {
            animationM.playAnim(0);
            point.x = point.x + Constants.xVelocity;
            movement = "left";
            MainUI.getInstance().PressButton(InputKey.None);

        }

        if (key == InputKey.Up)
        {
            animationM.playAnim(1);
            point.y = point.y + Constants.yVelocity;
            movement = "up";
            MainUI.getInstance().PressButton(InputKey.None);

        }

        if (key == InputKey.Down)
        {
            animationM.playAnim(3);
            point.y = point.y + Constants.yVelocity;
            movement = "down";
            MainUI.getInstance().PressButton(InputKey.None);
        }

        //animationM.playAnim(state);
        animationM.update();

    }

    public void reAdjust(Point point)
    {
        System.out.println("Adjusting");
        InputKey key = MainUI.getInstance().GetCurrentPress();
        if (movement == "left")
        {
            point.x = point.x + (-1 * Constants.xVelocity);
            update(point);
            //MainUI.getInstance().PressButton(InputKey.None);
        }

        if (movement == "right")
        {
            point.x = point.x + (-1 * Constants.xVelocity);
            update(point);
            //MainUI.getInstance().PressButton(InputKey.None);
        }

        if (movement == "up")
        {
            point.y = point.y + (-1 * Constants.yVelocity);
            update(point);
            //MainUI.getInstance().PressButton(InputKey.None);

        }

        if (movement == "down")
        {
            point.y = point.y + (-1 * Constants.yVelocity);
            update(point);
            //MainUI.getInstance().PressButton(InputKey.None);
        }
    }


}

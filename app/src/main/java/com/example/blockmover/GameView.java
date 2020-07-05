package com.example.blockmover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    //This class represent the view on the screen
    private MainThread thread;
    private SceneManager manager;

    int pointX = 1000;
    int pointY = 300;
    int col = 10;
    int row = 10;

    public GameView(Context context)
    {
        super(context);


        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);

        manager = new SceneManager();


        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

        //characterSprite.setX(startSprite.getX());
        //characterSprite.setY(startSprite.getY());
        //int testX = startSprite.getX();

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while(retry)
        {
            try {
                thread.setRunning(false);
                thread.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        manager.receiveTouch(event);
        return true;
        //return super.onTouchEvent(event);
    }

    public void update()
    {

        manager.update();

       /* float wy = (player.getRectangle().width() + gameObject.getRectangle().width()) * (playerPoint.x - gameObject.getRectangle().centerY());
        float hx = (player.getRectangle().height() + gameObject.getRectangle().height()) * (player.getRectangle().centerX() - gameObject.getRectangle().centerX());

        if (wy > hx)
        {
            if (wy > -hx)
            {
                //top
                System.out.println("Pushing Down");
                gameObjectPoint.y += 100;
            }
            else
            {
                //left
                System.out.println("Pushing Right");
                gameObjectPoint.x += 100;
            }
        }

        else
        {
            if (wy > -hx)
            {
                System.out.println("Pushing Left");
                gameObjectPoint.x -= 100;
            }
            else
            {
                System.out.println("Pushing Down");
                gameObjectPoint.y += 100;

            }
        }
        */
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        manager.draw(canvas);
    }
}

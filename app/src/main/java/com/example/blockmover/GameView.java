package com.example.blockmover;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    //This class represent the view on the screen
    private MainThread thread;
    private PlayerObject player;
    private Point playerPoint;

    public GameView(Context context)
    {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);
        //Create player object here
        player = new PlayerObject(new Rect(100,100,200,200), Color.rgb(100,0,0));
        playerPoint = new Point(150,150);

        //Create game object here


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
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int)event.getX(),(int)event.getY());
        }
        return true;
        //return super.onTouchEvent(event);
    }

    public void update()
    {
        //Handle on screen logic here
        player.update(playerPoint);
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (canvas != null)
        {
            canvas.drawColor(Color.WHITE);

            player.draw(canvas);
        }
    }
}

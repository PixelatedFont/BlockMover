package com.example.blockmover;

import android.content.Context;
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
    private InGameObject gameObject, gameObject2;
    private Point playerPoint;
    private Point gameObjectPoint, gameObjectPoint2;
    private MainUI buttonManager;

    public GameView(Context context)
    {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);
        //Create player object here
        player = new PlayerObject(new Rect(100,100,200,200), Color.rgb(100,0,0));

        playerPoint = new Point(600,600);

        //Create game object here
        gameObject = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,100,0), true);
        gameObjectPoint = new Point(100,100);

        gameObject2 = new InGameObject(new Rect(200, 200, 400, 400), Color.BLACK, false);
        gameObjectPoint2 = new Point(500,500);

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

        }
        return true;
        //return super.onTouchEvent(event);
    }

    public void update()
    {
        //Handle on screen logic here
        player.update(playerPoint);
        gameObject2.update(gameObjectPoint2);

        if (gameObject.playerCollide(player))
        {
            gameObjectPoint.x += 100;
            gameObject.update(gameObjectPoint);
        }

    }

    @Override
    public void draw(Canvas canvas)
    {

        //Draw here
        super.draw(canvas);
        if (canvas != null)
        {
            canvas.drawColor(Color.WHITE);

            player.draw(canvas);
            gameObject.draw(canvas);
            gameObject2.draw(canvas);
        }
    }
}

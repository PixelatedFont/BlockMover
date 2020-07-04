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
    private PlayerObject player;
    private InGameObject gameObject;
    private Point playerPoint;
    private Point gameObjectPoint;
    private InGameObject[][] gameObjects;
    private Point[][] points;


    int pointX = 1000;
    int pointY = 300;
    int col = 10;
    int row = 10;

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
        gameObjectPoint = new Point(800,600);

        gameObjects = new InGameObject[10][10];
        points = new Point[10][10];

        for (int i = 0; i < 10; i++ )
        {
            for (int j = 0; j < 10;j++)
            {
                gameObjects[i][j] = new InGameObject(new Rect(100,100,200,200), Color.rgb(100 + i+10,100+j,100), false);
            }
        }

        for (int i = 0; i < 10; i++ )
        {
            for (int j = 0; j < 10;j++)
            {
                System.out.println("Drawing: " + i +" " + j);
                points[i][j] = new Point(pointX,pointY);
            }
        }




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
        gameObject.update(gameObjectPoint);
        //System.out.println(Constants.xVelocity);
        //System.out.println(Constants.yVelocity);


        //System.out.println(gameObject.playerCollide(player));
        if (gameObject.playerCollide(player).equals("left"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.x -= 100;
        }

        if (gameObject.playerCollide(player).equals("right"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.x += 100;
        }

       if (gameObject.playerCollide(player).equals("top"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.y -= 100;
        }

        if (gameObject.playerCollide(player).equals("bottom"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.y += 100;
        }





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

        //Draw here
        super.draw(canvas);
        if (canvas != null)
        {
            canvas.drawColor(Color.WHITE);

            //player.draw(canvas);
            //gameObject.draw(canvas);






        }
    }
}

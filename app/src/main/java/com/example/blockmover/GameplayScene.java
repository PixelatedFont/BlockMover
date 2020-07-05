package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;


public class GameplayScene implements Scene
{
    private PlayerObject player;
    private InGameObject gameObject;
    private Point playerPoint;
    private Point gameObjectPoint;
    private InGameObject[][] gameObjects;
    private Point[][] points;

    public GameplayScene()
    {
        //Create player object here
        player = new PlayerObject(new Rect(100,100,200,200), Color.rgb(100,0,0));

        playerPoint = new Point(600,600);

        //Create game object here
        gameObject = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,100,0), true);
        gameObjectPoint = new Point(800,600);

    }
    @Override
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




    }

    @Override
    public void draw(Canvas canvas)
    {

        if (canvas != null)
        {
            //Draw here

            canvas.drawColor(Color.WHITE);

            player.draw(canvas);
            gameObject.draw(canvas);
        }

    }

    @Override
    public void terminate()
    {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event)
    {

    }
}

package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;


public class GameplayScene implements Scene
{
    private PlayerObject player;
    private InGameObject gameObject, gameObject2;
    private Point playerPoint, startPoint;
    private Point gameObjectPoint, gameObjectPoint2;
    private InGameObject[][] gameObjects;
    private Point[][] points;
    private MusicPlayer musicPlayer;
    private  int PointX = 400;
    private  int PointY = 200;

    public GameplayScene()
    {

        //musicPlayer = new MusicPlayer();
       // musicPlayer.playTrack();
        //Create player object here
        player = new PlayerObject(new Rect(100,100,200,200), Color.rgb(100,0,0));
        playerPoint = new Point(600,500);

        //Create game object here

        gameObject = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,100,0), true, true);
        gameObjectPoint = new Point(600,400);
        gameObject.update(gameObjectPoint);

        gameObject2 = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,0,100), true, false);
        gameObjectPoint2 = new Point(300,400);

        gameObjects = new InGameObject[5][5];
        points = new Point[5][5];

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
            {
                gameObjects[i][j] = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,100*i,10*j), true, false);
                points[i][j] = new Point(PointX,PointY);
            }

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
            {
                gameObjects[i][j].update(points[i][j]);
            }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
            {
                gameObjects[i][j].setCoordinate(PointX, PointY);
                PointX += 100;
            }
            PointX = 400;
            PointY += 100;
        }



    }
    @Override
    public void update()
    {

        //Handle on screen logic here
        player.update(playerPoint);
        //gameObject.update(gameObjectPoint);
        gameObject2.update(gameObjectPoint2);
        //System.out.println(Constants.xVelocity);
        //System.out.println(Constants.yVelocity);
        //System.out.println("I'm running boi");



        //System.out.println(gameObject.playerCollide(player));
        if (gameObject.playerCollide(player).equals("left"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.x -= 100;
            gameObject.update(gameObjectPoint);
        }

        if (gameObject.playerCollide(player).equals("right"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.x += 100;
            gameObject.update(gameObjectPoint);
        }

        if (gameObject.playerCollide(player).equals("top"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.y -= 100;
            gameObject.update(gameObjectPoint);
        }

        if (gameObject.playerCollide(player).equals("bottom"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.y += 100;
            gameObject.update(gameObjectPoint);
        }

        if (gameObject2.playerCollide(player).equals("0"))
        {
            player.reAdjust(playerPoint);

        }

        for (int i = 0; i < 5; i++)
        {
            gameObjects[i][0].setSprite(2);
            if (gameObjects[i][0].playerCollide(player).equals("0"))
            {
                player.reAdjust(playerPoint);
            }
        }

        for (int i = 0; i < 5; i++)
        {
            gameObjects[0][i].setSprite(2);
            if (gameObjects[0][i].playerCollide(player).equals("0"))
            {
                player.reAdjust(playerPoint);
            }
        }

        for (int i = 0; i < 5; i++)
        {
            gameObjects[i][4].setSprite(2);
            if (gameObjects[i][4].playerCollide(player).equals("0"))
            {
                player.reAdjust(playerPoint);
            }
        }

        for (int i = 0; i < 5; i++)
        {
            gameObjects[4][i].setSprite(2);
            if (gameObjects[4][i].playerCollide(player).equals("0"))
            {
                player.reAdjust(playerPoint);
            }
        }





    }

    @Override
    public void draw(Canvas canvas)
    {

        if (canvas != null)
        {
            //Draw here

            canvas.drawColor(Color.BLACK);

            canvas.translate(-player.getRectangle().centerX(), -player.getRectangle().centerY());
            canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);

            //canvas.translate(canvas.getWidth()/2 - playerPoint., canvas.getHeight()/2);


            gameObject.draw(canvas);
            gameObject2.draw(canvas);



            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                {
                    gameObjects[i][j].draw(canvas);

                }

            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                {
                    gameObjects[i][j].setSprite(0);

                }

            gameObject.setSprite(1);
            gameObject.draw(canvas);
            player.draw(canvas);
            //canvas.scale(5000,5000);
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

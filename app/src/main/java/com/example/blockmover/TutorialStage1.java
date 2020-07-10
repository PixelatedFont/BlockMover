package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;


public class TutorialStage1 implements Scene
{
    private PlayerObject player;
    private InGameObject gameObject, gameObject2;
    private Point playerPoint, startPoint;
    private Point gameObjectPoint, gameObjectPoint2;
    private InGameObject[][] boardObjects;
    private Point[][] boardPoints;
    private MusicPlayer musicPlayer;
    private  int PointX = 400;
    private  int PointY = 200;
    private int COL = 10;
    private int ROW = 5;
    private int total;

    MediaPlayer mediaPlayer;

    public TutorialStage1()
    {

        //musicPlayer = new MusicPlayer();
       // musicPlayer.playTrack();
        //Create player object here
        player = new PlayerObject(new Rect(100,100,200,200), Color.rgb(100,0,0));
        playerPoint = new Point(600,500);

        //Create game object here

        gameObject = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,100,0));
        gameObjectPoint = new Point(600,400);
        gameObject.update(gameObjectPoint);

        gameObject2 = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,0,100));
        gameObjectPoint2 = new Point(600,300);
        gameObject2.update(gameObjectPoint2);

        boardObjects = new InGameObject[ROW][COL];
        boardPoints = new Point[ROW][COL];

        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
            {
                boardObjects[i][j] = new InGameObject(new Rect(100,100,200,200), Color.rgb(0,100*i,10*j));
                boardPoints[i][j] = new Point(PointX,PointY);
            }

        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
            {
                boardObjects[i][j].update(boardPoints[i][j]);
            }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++)
            {
                boardObjects[i][j].setCoordinate(PointX, PointY);
                PointX += 100;
            }
            PointX = 400;
            PointY += 100;
        }

        mediaPlayer = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.moderato);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();



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


        //SETUP Board


        for (int i = 0; i < ROW; i++)
        {

            //Border to Player collision
            boardObjects[i][0].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[i][0].getRectangle(), player.getRectangle()))
            {
                player.reAdjust(playerPoint);
            }

            boardObjects[i][COL-1].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[i][COL-1].getRectangle(), player.getRectangle()))
            {
                player.reAdjust(playerPoint);
            }


        }

        for (int j = 0; j < COL; j++)
        {
            boardObjects[0][j].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[0][j].getRectangle(), player.getRectangle()))
            {
                player.reAdjust(playerPoint);
            }


            boardObjects[ROW-1][j].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[ROW-1][j].getRectangle(), player.getRectangle()))
            {
                player.reAdjust(playerPoint);
            }

        }

        if (ObjectCollisionManager.objectToPlayer(gameObject, player).equals("left") ||
                ObjectCollisionManager.objectToPlayer(gameObject, player).equals("right"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.x += Constants.xVelocity;
            gameObject.update(gameObjectPoint);
        }

        if (ObjectCollisionManager.objectToPlayer(gameObject, player).equals("top") ||
                ObjectCollisionManager.objectToPlayer(gameObject, player).equals("bottom"))
        {
            System.out.println(gameObject.playerCollide(player));
            gameObjectPoint.y += Constants.yVelocity;
            gameObject.update(gameObjectPoint);
        }


        //System.out.println(gameObject.playerCollide(player));

        if (gameObject2.objectCollide(gameObject))
        {
            player.reAdjust(playerPoint);
            gameObject.reAdjust(gameObjectPoint);
        }

        if (ObjectCollisionManager.objectSolid(gameObject2.getRectangle(), player.getRectangle()))
        {
            mediaPlayer.stop();

            if (!mediaPlayer.isPlaying())
            {
                mediaPlayer = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.intermission);
                mediaPlayer.start();
                try {
                    MainThread.sleep(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SceneManager.ACTIVE_SCENE = 1;
                mediaPlayer = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.objection);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
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

            for (int i = 0; i < ROW; i++)
                for (int j = 0; j < COL; j++)
                {
                    boardObjects[i][j].draw(canvas);

                }

            for (int i = 0; i < ROW; i++)
                for (int j = 0; j < COL; j++)
                {
                    boardObjects[i][j].setSprite(0);

                }

            gameObject.setSprite(1);
            gameObject.draw(canvas);
            gameObject2.setSprite(3);
            gameObject2.draw(canvas);
            player.draw(canvas);
            //canvas.scale(5000,5000);
        }

    }

    @Override
    public void terminate()
    {
        mediaPlayer.release();
        mediaPlayer.stop();
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event)
    {

    }
}

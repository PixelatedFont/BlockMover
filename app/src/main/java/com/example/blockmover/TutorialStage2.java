package com.example.blockmover;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class TutorialStage2 implements Scene
{
    private PlayerObject player;
    private Point playerPoint, startPoint;
    private InGameObject[][] boardObjects;
    private Point[][] boardPoints;
    private MediaPlayer mediaPlayer;

    private  int PointX = 400;
    private  int PointY = 200;
    private int COL = 10;
    private int ROW = 10;

    public TutorialStage2()
    {
        player = new PlayerObject(new Rect(100,100,200,200), Color.rgb(100,0,0));
        playerPoint = new Point(600,500);

        //Create game object here

        //
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




    }

    @Override
    public void update()
    {
        player.update(playerPoint);

        for (int i = 0; i < ROW; i++)
        {

            //Border to Player collision
            boardObjects[i][0].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[i][0].getRectangle(), player.getRectangle())) {
                player.reAdjust(playerPoint);
            }

            boardObjects[0][i].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[0][i].getRectangle(), player.getRectangle())) {
                player.reAdjust(playerPoint);
            }

            boardObjects[i][COL - 1].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[i][COL - 1].getRectangle(), player.getRectangle())) {
                player.reAdjust(playerPoint);
            }

            boardObjects[ROW - 1][i].setSprite(2);
            if (ObjectCollisionManager.objectSolid(boardObjects[ROW - 1][i].getRectangle(), player.getRectangle())) {
                player.reAdjust(playerPoint);
            }
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
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

        player.draw(canvas);
    }

    @Override
    public void terminate()
    {
        mediaPlayer.release();
        mediaPlayer.stop();
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}

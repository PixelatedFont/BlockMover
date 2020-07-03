package com.example.blockmover;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Disable title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Set screen to Landscape Mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Get screen height and width
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        //Create ButtonManager

        //FrameLayout
        FrameLayout game = new FrameLayout(this);
        GameView gameView = new GameView(this);
        RelativeLayout gameWidgets = new RelativeLayout(this);

        MainUI.getInstance().Init(this);

        gameWidgets.addView(MainUI.getInstance().getButton(0));
        gameWidgets.addView(MainUI.getInstance().getButton(1));
        gameWidgets.addView(MainUI.getInstance().getButton(2));
        gameWidgets.addView(MainUI.getInstance().getButton(3));




        game.addView(gameView);
        game.addView(gameWidgets);


        //Set view on screen
        setContentView(game );

    }

}
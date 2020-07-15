package com.example.blockmover;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;



public class MainUI {
    private static MainUI instance;


    private ArrayList<Button> gameButtons;
    private MainActivity mainActivity;
    private InputKey currentPress;

    public static MainUI getInstance() {
        if (instance == null)
            instance = new MainUI();

        return instance;
    }

    private MainUI() {

    }

    public void Init(MainActivity main) {
        gameButtons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            gameButtons.add(new GameButton(main));
        }

        // 0 = Left
        // 1 = Up
        // 2 = Right
        // 3 = Down

        gameButtons.get(0).setText("LEFT");
        gameButtons.get(0).setX(0);
        gameButtons.get(0).setY(Constants.SCREEN_HEIGHT - 400);

        gameButtons.get(1).setText("Up");
        gameButtons.get(1).setX(200);
        gameButtons.get(1).setY(Constants.SCREEN_HEIGHT - 600);

        gameButtons.get(2).setText("RIGHT");
        gameButtons.get(2).setX(400);
        gameButtons.get(2).setY(Constants.SCREEN_HEIGHT - 400);

        gameButtons.get(3).setText("Down");
        gameButtons.get(3).setX(200);
        gameButtons.get(3).setY(Constants.SCREEN_HEIGHT - 200);


        gameButtons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.yVelocity = 0;
                Constants.xVelocity = -100;
                PressButton(InputKey.Left);
                //System.out.println(instance.currentPress.toString());
            }
        });

        gameButtons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.xVelocity = 0;
                Constants.yVelocity = -100;
                PressButton(InputKey.Up);

                //System.out.println(instance.currentPress.toString());

            }
        });

        gameButtons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.yVelocity = 0;
                Constants.xVelocity = 100;
                PressButton(InputKey.Right);
                //System.out.println(instance.currentPress.toString());

            }
        });

        gameButtons.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.xVelocity = 0;
                Constants.yVelocity = 100;
                PressButton(InputKey.Down);
                //System.out.println(instance.currentPress.toString());

            }
        });
        this.mainActivity = main;
    }

    public void addButton(GameButton button) {
        gameButtons.add(button);
    }

    public Button getButton(int index) {
        return gameButtons.get(index);
    }

    public void PressButton(InputKey key) {
        this.currentPress = key;

    }

    public InputKey GetCurrentPress() {
        return this.currentPress;
    }

    public void stopKey(GameButton button) {
        if (button.isPressed() == false) {
            MainUI.getInstance().PressButton(InputKey.None);
        }
    }

    public void hideUI()
    {
        for (int i = 0; i < gameButtons.size(); i++)
        {
            gameButtons.get(i).setVisibility(View.INVISIBLE);
        }
    }





}

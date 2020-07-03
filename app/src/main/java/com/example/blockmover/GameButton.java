package com.example.blockmover;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class GameButton extends androidx.appcompat.widget.AppCompatButton
{
    public String btnName;
    private InputKey inputKey;


    public GameButton(Context context)
    {
        super(context);
        this.setWidth(100);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        // Here you need to declare what paint you canvas, e.g.:
        canvas.drawColor(Color.GREEN); // Set color

        super.onDraw(canvas);
    }

    public String getBtnName() {
        return this.btnName;
    }

}

package com.example.blockmover;

import android.graphics.Canvas;

public interface GameObject
{
    //This interface determine if a class if a GameObject
    public void draw(Canvas canvas);
    public void update();
}

package com.example.blockmover;

import android.graphics.Rect;

public class ObjectCollisionManager
{
    private InGameObject[] inGameObjects;
    public ObjectCollisionManager()
    {

    }

    public static boolean objectSolid(Rect rect, Rect rect2)
    {
        if (Rect.intersects(rect, rect2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String objectToPlayer(InGameObject object1, PlayerObject player)
    {
        if (Rect.intersects(object1.getRectangle(), player.getRectangle())) {
            if (Constants.xVelocity > 0) {
                return "right";
            }

            if (Constants.xVelocity < 0) {
                return "left";
            }

            if (Constants.yVelocity > 0) {
                return "bottom";
            }

            if (Constants.yVelocity < 0) {
                return "top";
            }

        }
        return "null";
    }



}

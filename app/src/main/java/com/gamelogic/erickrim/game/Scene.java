package com.gamelogic.erickrim.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by erickrim on 10/08/2017.
 */

public interface Scene {
    void draw(Canvas canvas);
    void update();
    void receiveTouch(MotionEvent event);
    void terminate();
}

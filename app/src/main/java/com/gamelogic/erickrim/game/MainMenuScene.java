package com.gamelogic.erickrim.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by erickrim on 10/08/2017.
 */

public class MainMenuScene implements Scene {


    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            terminate();
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1;
    }
}

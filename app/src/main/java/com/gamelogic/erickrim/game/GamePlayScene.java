package com.gamelogic.erickrim.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by erickrim on 10/08/2017.
 */

public class GamePlayScene implements  Scene {

    private Player player;
    private Point playerPoint;

    private SensorData sensorData;

    public GamePlayScene() {

        player = new Player(new Rect(0, 0, 128, 128), Color.BLUE);
        playerPoint = new Point(156, 960);

        sensorData = new SensorData();
    }

    @Override
    public void draw(Canvas canvas) {
        player.draw(canvas);
    }

    @Override
    public void update() {
        if (sensorData.getAccelerometer() != null) {
            playerPoint.set((int)sensorData.getAccelerometer()[0],960);
        }

        player.update(playerPoint);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            playerPoint.set((int) event.getX(), 960); // instead of event.getY() we want it constant at 960
        }
    }

    @Override
    public void terminate() {

    }
}

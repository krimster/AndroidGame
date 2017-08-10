package com.gamelogic.erickrim.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by erickrim on 10/08/2017.
 */

class Game extends SurfaceView implements SurfaceHolder.Callback {

    //TODO : use DI instead of a static context
    public static Context CURRENT_CONTEXT; // memory leak
    private GameThread thread;

    SceneManager manager;

    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);
        CURRENT_CONTEXT = context;
        thread = new GameThread(getHolder(), this);
        manager = new SceneManager();
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            thread.setRunning(true);
            thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.receiveTouch(event);
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        manager.draw(canvas);

    }

    public void update() {
        manager.update();
    }
}

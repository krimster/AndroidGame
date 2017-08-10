package com.gamelogic.erickrim.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by erickrim on 10/08/2017.
 */

public class GameThread extends Thread {


    private int FPS = 30; // Frames per second cap
    private double avgFPS;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private boolean running;
    private static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, Game game) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }


    @Override
    public void run() {
        long startTime;
        long timeMills;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;


        // game loop
        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas(); // lock canvas
                synchronized (surfaceHolder) {
                    this.game.update();
                    this.game.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            timeMills = (System.nanoTime() - startTime) / 1000000; // time in milliseconds
            waitTime = targetTime - timeMills;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == FPS) {
                avgFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avgFPS);
            }

        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }


}

package com.gamelogic.erickrim.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by erickrim on 10/08/2017.
 */

public class Animation {

    private Bitmap[] frames;
    private int frameIndex;
    private float frameTime; // how long a frame lasts for
    private long lastFrame;
    private boolean isPlaying = false;

    public Animation(Bitmap[] frames, float time) {
        this.frames = frames;
        frameIndex = 0;
        frameTime = time / frames.length;
        lastFrame = System.currentTimeMillis();
    }


    public void draw(Canvas canvas, Rect dest) {

        if (!isPlaying) {
            return;
        }

        canvas.drawBitmap(frames[frameIndex], null, dest, new Paint());
    }

    public void update() {
        if (!isPlaying) {
            return;
        }

        if (System.currentTimeMillis() - lastFrame > frameTime * 1000) {
            frameIndex++;

            if (frameIndex >= frames.length) {
                frameIndex = 0;
            }

            lastFrame = System.currentTimeMillis();
        }
    }

    public void play() {
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
        isPlaying = true;
    }

    public void stop() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

}

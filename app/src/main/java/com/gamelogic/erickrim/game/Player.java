package com.gamelogic.erickrim.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by erickrim on 10/08/2017.
 */

public class Player {

    private Rect rect;
    private Paint paint;

    private Animation idle;
    private Animation walk;
    private AnimationManager animationManager;

    public Player(Rect rect, int color) {
        this.rect = rect;
        this.paint = new Paint();
        paint.setColor(color);

        BitmapFactory bf = new BitmapFactory();
        Bitmap img_motorcycle =  bf.decodeResource(Game.CURRENT_CONTEXT.getResources(), R.drawable.ic_motorcycle);
        Bitmap img_plane_takeoff =  bf.decodeResource(Game.CURRENT_CONTEXT.getResources(), R.drawable.ic_flight);
        Bitmap img_plane_land =  bf.decodeResource(Game.CURRENT_CONTEXT.getResources(), R.drawable.ic_flight_land);

        idle = new Animation(new Bitmap[]{img_motorcycle}, 1F);
        walk = new Animation(new Bitmap[]{img_plane_takeoff, img_plane_land}, 0.5F);

        animationManager = new AnimationManager(new Animation[] {idle, walk});
    }

    public void draw(Canvas canvas) {
        animationManager.draw(canvas, rect);

    }

    /**
     * gets a Point object (x/y values) and will adjust it
     * to the Rect format
     * @param point Object with x and y values
     */
    public void update(Point point) {
        float initialPosition = rect.left;


        rect.set(point.x -  rect.width()/2, point.y - rect.height()/2,
                 point.x +  rect.width()/2, point.y + rect.height()/2);

        int state = 0;
        if(rect.left - initialPosition > 5 || rect.left - initialPosition < -5) {
            state = 1;
        }

        animationManager.playAnimation(state);
        animationManager.update();
    }
}

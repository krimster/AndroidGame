package com.gamelogic.erickrim.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by erickrim on 10/08/2017.
 */

public class SensorData implements SensorEventListener {


    private SensorManager manager;
    private Sensor sensor;
    private float[] accelerometer;


    public SensorData() {
        manager = (SensorManager) Game.CURRENT_CONTEXT.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accelerometer = event.values;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float[] getAccelerometer() {
        return accelerometer;
    }
}

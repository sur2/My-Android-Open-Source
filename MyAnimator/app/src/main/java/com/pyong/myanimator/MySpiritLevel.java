package com.pyong.myanimator;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MySpiritLevel implements SensorEventListener {
    private final String TAG = MySpiritLevel.class.getName();
    protected SensorManager mSensorManager;
    public float mX;
    public float mY;
    public float mZ;
    private Handler xyDisplayHandler;

    public MySpiritLevel(Context context, Handler xyDisplayHandler) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

        mX = 0.0f;
        mY = 0.0f;
        mZ = 0.0f;
        this.xyDisplayHandler = xyDisplayHandler;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //SensorEvent.values[0]: x축 - 단말기 측면(-10 ~ 10)
        //SensorEvent.values[1]: y축 - 단말기 상하(-10 ~ 10)
        //SensorEvent.values[2]: z축 - 단말기 화면(-10 ~ 10)
        mX = Math.round(event.values[0] * 1_000_000) / 1_000_000f;
        mY = Math.round(event.values[1] * 1_000_000) / 1_000_000f;
        mZ = Math.round(event.values[2] * 1_000_000) / 1_000_000f;
        Log.i(TAG, "onSensorChanged: [x" + mX + "], [y: " + mY + "], [z: " + mZ + "]");
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putFloat("getX", mX);
        bundle.putFloat("getY", mY);
        bundle.putFloat("getZ", mY);
        msg.setData(bundle);
        msg.what = MainActivity.XY_POINT_MSG;
        xyDisplayHandler.sendMessage(msg);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

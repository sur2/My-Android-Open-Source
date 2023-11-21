package com.pyong.myanimator;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    private static final String TAG = MyTimer.class.getSimpleName();
    private Timer mTimer;
    private TimerTask mTimerTask;
    private Handler mainHandler;
    private int mCnt;
    private Vibrator mVibrator;
    public boolean isRunning;

    public MyTimer(Context context, Handler mainHandler) {
        mTimer = new Timer();
        mTimerTask = getTimerTask();
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        this.mainHandler = mainHandler;
    }

    private TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                mCnt++;
                if (isRunning == false) {
                    isRunning = true;
                }
                Log.d(TAG, "Timer: " + mCnt + "s");
                if (mCnt > 6 && mCnt <= 7) {
                    /* 수평감지 */
                    new Handler(Looper.getMainLooper()).post(() -> {
                        mVibrator.vibrate(VibrationEffect.createOneShot(128, 255));
                        mainHandler.sendEmptyMessage(MainActivity.DETECT_MSG);
                    });
                }
            }
        };
    }

    public int getCnt() {
        return mCnt;
    }

    public void start() {
        stop();
        mTimer = new Timer();
        mTimerTask = getTimerTask();
        mTimer.schedule(mTimerTask, 0, 100);
        isRunning = true;
    }

    public void stop() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimerTask = null;
        mTimer = null;
        mCnt = 0;
        isRunning = false;
    }
}


package com.example.cameracallback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Event";

    private Button mButton;

    private CameraManager mCameraManager;
    private CameraEventCallback mCameraEventCallback;

    private Semaphore mSemaphore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSemaphore = new Semaphore(0);

        mCameraManager = (CameraManager) getSystemService(Service.CAMERA_SERVICE);
        mCameraEventCallback = new CameraEventCallback(mSemaphore);

        try {
            mCameraEventCallback.initCameraState(mCameraManager.getCameraIdList().length);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v -> {
            Thread thread = new Thread(new Runnable() {
                Handler handler = new Handler();
                @Override
                public void run() {
                    Log.i(TAG, "Thread" + Thread.currentThread().toString());
                    Log.i(TAG, "before register callback");
                    mCameraManager.registerAvailabilityCallback(mCameraEventCallback, handler);
                    Log.i(TAG, "after register callback");
                    Log.i(TAG, "before unregister callback");
                    mCameraManager.unregisterAvailabilityCallback(mCameraEventCallback);
                    Log.i(TAG, "after unregister callback");
                    mSemaphore.release();
                }
            });
            thread.start();
            try {
                mSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "Camera Open? " + mCameraEventCallback.isOpenCamera());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraManager != null && mCameraEventCallback != null)
            mCameraManager.unregisterAvailabilityCallback(mCameraEventCallback);
    }
}

class CameraEventCallback extends CameraManager.AvailabilityCallback {

    private static final String TAG = "Event";

    boolean[] mOpenCameraArray;
    Semaphore mSemaphore;

    public CameraEventCallback(Semaphore s) {
        this.mSemaphore = s;
    }

    public void initCameraState(int size) {
        mOpenCameraArray = new boolean[size];
    }

    @Override
    public void onCameraAvailable(@NonNull String cameraId) {
        super.onCameraAvailable(cameraId);
        /*try {
            mSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        mOpenCameraArray[Integer.parseInt(cameraId)] = false;
        Log.i(TAG, "callback camera available " + cameraId + ", Thread" + Thread.currentThread().toString());
    }

    @Override
    public void onCameraUnavailable(@NonNull String cameraId) {
        super.onCameraUnavailable(cameraId);
        /*try {
            mSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        mOpenCameraArray[Integer.parseInt(cameraId)] = true;
        Log.i(TAG, "callback camera unavailable" + cameraId + ", Thread" + Thread.currentThread().toString());
    }

    public boolean isOpenCamera() {
        for (boolean isOpen : mOpenCameraArray) {
            if (isOpen)
                return true;
        }
        return false;
    }

}
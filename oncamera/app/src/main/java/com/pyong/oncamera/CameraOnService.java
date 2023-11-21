package com.pyong.oncamera;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CameraOnService extends AccessibilityService {

    Button openBtn;
    ConstraintLayout cameraLayout;
    View.OnTouchListener onTouchListener;

    float currentTouchX, currentTouchY;
    int currentViewX, currentViewY;

    WindowManager.LayoutParams params;
    WindowManager windowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        cameraLayout = new ConstraintLayout(this);
        onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentTouchX = event.getRawX();
                        currentTouchY = event.getRawY();
                        currentViewX = params.x;
                        currentViewY = params.y;
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int x = (int) (event.getRawX() - currentTouchX);
                        int y = (int) (event.getRawY() - currentTouchY);
                        params.x = currentViewX + x;
                        params.y = currentViewY + y;
                        windowManager.updateViewLayout(cameraLayout, params);
                        break;
                }
                return true;
            }
        };

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT
        );

        params.x = 0;
        params.y = 0;

        inflater.inflate(R.layout.button_activity, cameraLayout);
        openBtn = cameraLayout.findViewById(R.id.open_button);
        openBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        cameraLayout.setOnTouchListener(onTouchListener);
        windowManager.addView(cameraLayout, params);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }
}

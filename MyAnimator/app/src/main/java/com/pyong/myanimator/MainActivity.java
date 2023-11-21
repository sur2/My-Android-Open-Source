package com.pyong.myanimator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int XY_POINT_MSG = 0;
    public static final int DETECT_MSG = 1;

    private static float startAngle = 0;
    private static float endAngle = 360;

    private Button animatorBtn;
    private ImageView mCircleView;
    private ConstraintLayout mainLayout;
    private float circleX;
    private float circleY;

    private TextView xPointTv;
    private TextView yPointTv;
    private TextView centerTv;
    private ValueAnimator spiritLevelAnimator;
    public Handler xyDisplayHandler;

    private MyTimer myTimer;

    private DirectionLineView directionLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.cl_main);
        xPointTv = findViewById(R.id.tv_x_point);
        yPointTv = findViewById(R.id.tv_y_point);
        centerTv = findViewById(R.id.center_text_view);

        xyDisplayHandler = getXYDisplayHandler();
        spiritLevelAnimator = ValueAnimator.ofFloat();

        directionLineView = findViewById(R.id.direction_line_image_view);

        new MySpiritLevel(this, xyDisplayHandler);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startAngle, endAngle);
        valueAnimator.setDuration(1000);

        mCircleView = findViewById(R.id.circle_image_view);
        myTimer = new MyTimer(this, xyDisplayHandler);

        animatorBtn = findViewById(R.id.animator_button);
        animatorBtn.setOnClickListener(v -> {
            //myTimer.start();
            directionLineView.start();
        });

        circleX = mCircleView.getX();
        circleY = mCircleView.getY();

        defaultSpiritUI();

      /*  valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                Log.i("MainActivity", "onAnimationUpdate: " + animation.getAnimatedValue());
            }
        });*/

    }

    public void startAnimator(View view) {

    }

    public Handler getXYDisplayHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void dispatchMessage(@NonNull Message msg) {
                super.dispatchMessage(msg);

                if (msg.what == XY_POINT_MSG) {
                    float getX = (float) msg.getData().get("getX");
                    float getY = (float) msg.getData().get("getY");
                    float getZ = (float) msg.getData().get("getZ");

                    xPointTv.setText("x: " + getX);
                    yPointTv.setText("y: " + getY);

                    String center = String.format("%.1f", Math.abs(Math.max(getX, getY)));
                    centerTv.setText(center);

                    mCircleView.setTranslationX(getX * -100);
                    mCircleView.setTranslationY(getY * 100);

                    if (isCenter(getX, getY) && !myTimer.isRunning) {
                        myTimer.start();
                    }
                    if (!isCenter(getX, getY)) {
                        if (myTimer.getCnt() > 0) {
                            myTimer.stop();
                            defaultSpiritUI();
                        }
                    }
                }

                if (msg.what == DETECT_MSG) {
                    detectSpiritUI();
                }

            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myTimer != null) {
            myTimer.stop();
        }
    }

    public boolean isCenter(float x, float y) {
        if (-1 < x && x < 1
                && -1 < y && y < 1) {
            return true;
        } else {
            return false;
        }
    }

    public void detectSpiritUI() {
        mainLayout.setBackgroundColor(getColor(R.color.darkBackground));
        mCircleView.setBackground(getDrawable(R.drawable.detected_circle_bg));
    }
    public void defaultSpiritUI() {
        mainLayout.setBackgroundColor(getColor(R.color.white));
        mCircleView.setBackground(getDrawable(R.drawable.circle_bg));
    }

}
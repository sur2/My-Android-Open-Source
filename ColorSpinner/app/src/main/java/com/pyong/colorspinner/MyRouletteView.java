package com.pyong.colorspinner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import androidx.annotation.Nullable;

public class MyRouletteView extends View {
    public static final String TAG = "MySpinnerView";

    private Paint strokePaint;
    private Paint fillPaint;
    private Paint textPaint;

    private RectF rectF;

    private int size = 7;
    private String[] textList;

    public MyRouletteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        strokePaint = new Paint();
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(15f);
        strokePaint.setAntiAlias(true);

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60f);
        textPaint.setTextAlign(Paint.Align.CENTER);

        rectF = new RectF();

        textList = new String[size];
        for (int i = 0; i < size; i++) {
            textList[i] = i + "";
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float rectLeft = (float) getLeft() + (float) getPaddingLeft();
        float rectRight = (float) getRight() - (float) getPaddingRight();
        float rectTop = (float) getHeight() / 2f - rectRight / 2f + (float) getPaddingTop();
        float rectBottom = (float) getHeight() / 2f + rectRight / 2f - (float) getPaddingRight();
        Log.i(TAG, "[RectF]\nLeft: " + rectLeft + "\nRight: " + rectRight + "\nTop: " + rectTop + "\nBottom: " + rectBottom);
        rectF.set(rectLeft, rectTop, rectRight, rectBottom);
        canvas.drawRect(rectF, strokePaint);
        drawSpinner(canvas, rectF);
    }

    private void drawSpinner(Canvas canvas, RectF rectF) {
        canvas.drawArc(rectF, 0f, 360f, true, strokePaint);
        if (size > 1 && size < 9) {
            float sweepAngle = 360f / (float) size;
            // 원의 중심 X, Y
            float centerX = (rectF.left + rectF.right) / 2;
            float centerY = (rectF.top + rectF.bottom) / 2;
            // 원의 반지름
            float radius = (float) ((rectF.right - rectF.left) / 2 * 0.5);
            String[] colors = new String[]{"#fe4a49", "#2ab7ca", "#fed766", "#e6e6ea", "#f6abb6", "#005b96", "#7bc043", "#f37735"};
            for (int i = 0; i < size; i++) {
                fillPaint.setColor(Color.parseColor(colors[i]));
                float startAngle = i == 0 ? 0f : sweepAngle * i;
                canvas.drawArc(rectF, startAngle, sweepAngle, true, fillPaint);
                // 내부 중앙 각도
                float medianAngle = (float) ((startAngle + sweepAngle / 2f) * Math.PI / 180f);
                // 텍스트 좌표
                float textX = (float) (centerX + (radius * Math.cos(medianAngle)));
                float textY = (float) (centerY + (radius * Math.sin(medianAngle)));
                String text = (i > textList.length - 1) ? "empty" : textList[i];
                canvas.drawText(text, textX, textY, textPaint);
            }
        } else {
            throw new RuntimeException("Size out of Spinner");
        }
    }

    /**
     * 룰렛 회전 애니메이션
     * @param degree 전체 회전 길이(각)
     * @param duration 회전 시간
     * @param rouletteListener 결과 Callback
     */
    public void spin(float degree, long duration, MyRouletteListener rouletteListener) {
        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                rouletteListener.onSpinStart();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rouletteListener.onSpinEnd(getRotateResult(degree));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };

        // 시작 각도, 회전 각도, x축 타입, x축 값, y축 타입, y축 값
        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillAfter(true); // 애니메이션이 종료된 상태
        rotateAnimation.setAnimationListener(animationListener);

        startAnimation(rotateAnimation);
    }

    private String getRotateResult(float degree) {
        float moveDegree = degree % 360;
        float resultAngle = moveDegree > 270 ? 360 - moveDegree + 270 : 270 - moveDegree;
        for (int i = 1; i <= size; i++) {
            if (resultAngle < (360 / size) * i) {
                if (i - 1 >= size) {
                    return "empty";
                }

                return textList[i - 1];
            }
        }
        return "";
    }
}

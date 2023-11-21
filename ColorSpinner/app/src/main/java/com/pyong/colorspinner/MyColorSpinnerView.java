package com.pyong.colorspinner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyColorSpinnerView extends View implements View.OnTouchListener, View.OnHoverListener {

    private float width = 0f;
    private float height = 0f;
    private float centerX = 0f;
    private float centerY = 0f;

    private Paint strokePaint;
    private Paint fillPaint;

    private RectF rectF;
    private Context context;

    private String[] colors = new String[]{"#fe4a49", "#2ab7ca", "#fed766", "#e6e6ea", "#f6abb6", "#005b96", "#7bc043", "#f37735"};
    private int size = 8;

    public MyColorSpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        setOnTouchListener(this::onTouch);
        setOnHoverListener(this::onHover);
    }

    private void init() {
        centerX = getX() + (float) (getWidth() / 2);
        centerY = getY() + (float) (getHeight() / 2);

        strokePaint = new Paint();
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(15f);
        strokePaint.setAntiAlias(true);

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);

        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int min = Math.min(canvas.getWidth(), canvas.getHeight());
        width = min;
        height = min;
        rectF.set(0, 0, width, height);
        canvas.drawRect(rectF, strokePaint);

        float sweepAngle = 360f / size;
        for (int i = 0; i < size; i++) {
            float startAngle = i * sweepAngle;
            fillPaint.setColor(Color.parseColor(colors[i]));
            canvas.drawArc(rectF, startAngle, sweepAngle, true, fillPaint);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        Log.i("onTouch", "Action: " + event.getAction() + ", x: " + currentX + ", y: " + currentY);
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float degree = event.getY();
            setRotation(degree);
        }
        return false;
    }

    @Override
    public boolean onHover(View v, MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        Log.i("onHover", "Action: " + event.getAction() + ", x: " + currentX + ", y: " + currentY);
        return false;
    }
}

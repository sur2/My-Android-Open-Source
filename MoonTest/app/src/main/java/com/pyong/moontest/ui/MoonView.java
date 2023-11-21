package com.pyong.moontest.ui;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MoonView extends View {
    private Paint paint;
    private float radius = 0f;
    private float x, y;
    private float scale = 1f;

    public MoonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        radius = 500f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x = canvas.getWidth() / 2;
        y = canvas.getHeight() / 2;
        float angle = 0f;
        float startAngle = 0f;
        float sweepAngle = 360f;
        //radius = Math.min(x, y);
        canvas.drawCircle(x, y, radius, paint);
    }

    public void setScale(float scale) {
        this.scale = scale;
        invalidate();
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "radius", 0f);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();

    }
}

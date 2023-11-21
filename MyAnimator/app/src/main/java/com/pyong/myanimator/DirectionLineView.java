package com.pyong.myanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DirectionLineView extends AppCompatImageView {
    private static final String TAG = DirectionLineView.class.getSimpleName();
    public int width = 0;
    public int height = 0;
    private ValueAnimator valueAnimator;
    private ConstraintLayout.LayoutParams mParams;
    private boolean isFirst = false;

    public DirectionLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "DirectionLineView(context, attrs)");

        isFirst = true;
    }

    private ValueAnimator.AnimatorUpdateListener getListener() {
        return animation -> {
            int value = (int) animation.getAnimatedValue();
            mParams.width = value;
            super.setLayoutParams(mParams);
            Log.i(TAG, "Animation UpdateListener " + value);
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isFirst) {
            width = canvas.getWidth();
            height = canvas.getHeight();
            isFirst = false;

            mParams = (ConstraintLayout.LayoutParams) this.getLayoutParams();

            valueAnimator = ValueAnimator.ofInt(0, width);
            valueAnimator.setDuration(500);
            valueAnimator.addUpdateListener(getListener());
        }

        Log.d(TAG, "call onDraw(canvas), w: " + width + ", h: " + height);
    }

    public void start() {
        valueAnimator.start();
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    public void cancel() {
        valueAnimator.cancel();
    }
}

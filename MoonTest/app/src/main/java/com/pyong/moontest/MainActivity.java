package com.pyong.moontest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.pyong.moontest.ui.MoonView;

public class MainActivity extends AppCompatActivity {
    private MoonView moonView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moonView = findViewById(R.id.moon_view);
        button = findViewById(R.id.button);
        button.setOnClickListener((v)->{
            //moonView.startAnimation();
            ObjectAnimator animator = ObjectAnimator.ofFloat(moonView, "radius", 0f, 100f);
            animator.setDuration(2000);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.start();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
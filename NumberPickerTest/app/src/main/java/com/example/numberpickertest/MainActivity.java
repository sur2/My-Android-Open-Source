package com.example.numberpickertest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_HOUR = 99;
    private int MAX_MINUTE = 59;
    private int MAX_SECOND = 59;

    private MyNumberPicker npHour;
    private MyNumberPicker npMinute;
    private MyNumberPicker npSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        npHour = findViewById(R.id.np_hour);
        npMinute = findViewById(R.id.np_minute);
        npSecond = findViewById(R.id.np_second);

        npHour.setMaxValue(MAX_HOUR);
        npMinute.setMaxValue(MAX_MINUTE);
        npSecond.setMaxValue(MAX_SECOND);

    }


}
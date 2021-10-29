package com.example.numberpickertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MAX_HOUR = 99;
    private int MAX_MINUTE = 59;
    private int MAX_SECOND = 59;

    private MyNumberPicker npHour;
    private MyNumberPicker npMinute;
    private MyNumberPicker npSecond;

    private Button btStopAndGo;

    private Timer timer = null;
    private TimerTask timerTask = null;

    private boolean isStop = false;

    private int totalNumber = 0;

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

        btStopAndGo = findViewById(R.id.bt_stopAndGo);
        btStopAndGo.setOnClickListener(this);
        isStop = false;

        timer = new Timer();
        timerTask = new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                Log.d("Timer", "count : " + count++);
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btStopAndGo.getId()) {
            if (isStop == false) {
                isStop = true;
                btStopAndGo.setText("Stop");
                timer.schedule(timerTask, 0, 1000);
            }
            else {
                isStop = false;
                btStopAndGo.setText("Go");
                timer.cancel();
            }
            Toast.makeText(this, "Total " + getTotalNumber(), Toast.LENGTH_SHORT).show();
        }
    }

    public int getTotalNumber() {
        return npHour.getValue() * 3600 + npMinute.getValue() * 60 + npSecond.getValue();
    }

}
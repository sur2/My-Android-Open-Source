package com.example.checkservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnBind;
    Button mBtnData;
    MessengerClient messengerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messengerClient = new MessengerClient(getApplicationContext());

        mBtnBind = findViewById(R.id.btn_bind);
        mBtnBind.setOnClickListener(v -> {
            /*new Handler(Looper.getMainLooper()).post(() -> {
                messengerClient.bindService();
            });*/
            messengerClient.bindService();
            new Handler(Looper.getMainLooper()).post(() -> {
                messengerClient.callMethod();
            });
            /*new Handler(Looper.getMainLooper()).post(()->{
                Log.i("MainLooper", "end1... inner handler");
            });
            Log.i("MainLooper", "end2... inner handler");*/
            Log.i("MainAct", "onClick end...");
        });
        mBtnData = findViewById(R.id.btn_data);
        mBtnData.setOnClickListener(v -> {
            ;
        });
        Log.i("MainAct", "onCreate end...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        messengerClient.unbindService();
    }
}
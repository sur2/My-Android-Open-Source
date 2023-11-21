package com.pyong.servicea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TEST::MainActivity";
    public static final String serviceAction = "com.pyong.service.A";
    Button startServiceBtn;
    Button endServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceBtn = findViewById(R.id.start_button);
        startServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(serviceAction);
            intent.setPackage(getPackageName());
            startService(intent);
        });

        endServiceBtn = findViewById(R.id.end_button);
        endServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(serviceAction);
            intent.setPackage(getPackageName());
            stopService(intent);
        });
    }
}
package com.pyong.clienta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TEST-A";
    public static final String serviceAction = "com.pyong.service.A";
    public static final String targetPackage = "com.pyong.servicea";

    Button bindServiceBtn;
    Button unbindServiceBtn;

    Button bindServiceBtn2;
    Button unbindServiceBtn2;

    ServiceConnection serviceConnection;
    ServiceConnection serviceConnection2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected from " + name.getClassName());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected from " + name.getClassName());
            }
        };

        serviceConnection2 = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "(2) onServiceConnected from " + name.getClassName());

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "(2) onServiceDisconnected from " + name.getClassName());
            }
        };

        bindServiceBtn = findViewById(R.id.bind_button);
        bindServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(serviceAction);
            intent.setPackage(targetPackage);
            startService(intent);
            bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        });
        unbindServiceBtn = findViewById(R.id.unbind_button);
        unbindServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(serviceAction);
            intent.setPackage(targetPackage);
            unbindService(serviceConnection);
        });

        bindServiceBtn2 = findViewById(R.id.bind_button2);
        bindServiceBtn2.setOnClickListener(v -> {
            Intent intent = new Intent(serviceAction);
            intent.setPackage(targetPackage);
            bindService(intent, serviceConnection2, Service.BIND_AUTO_CREATE);
        });
        unbindServiceBtn2 = findViewById(R.id.unbind_button2);
        unbindServiceBtn2.setOnClickListener(v -> {
            Intent intent = new Intent(serviceAction);
            intent.setPackage(targetPackage);
            unbindService(serviceConnection2);
        });
    }
}
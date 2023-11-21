package com.pyong.airplanemodeonoff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;

import java.security.spec.ECField;

public class MainActivity extends AppCompatActivity {

    private final static int REQ_CODE = 1;

    private Button airplaneOnBtn;
    private Button airplaneOffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airplaneOnBtn = findViewById(R.id.airplane_mode_on_button);
        airplaneOffBtn = findViewById(R.id.airplane_mode_off_button);

        airplaneOnBtn.setOnClickListener(v -> {
            try {
                //Runtime.getRuntime().exec("adb shell");
                //Runtime.getRuntime().exec("settings put global airplane_mode_on 1");
                Runtime.getRuntime().exec("ls -l");
                int airplaneMode = android.provider.Settings.System.getInt(this.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);
                if (airplaneMode == 0) {
                    boolean res = android.provider.Settings.System.putInt(this.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 1);
                    Log.d("Main", "res: " + res);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_SECURE_SETTINGS,
            Manifest.permission.WRITE_SETTINGS}, REQ_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
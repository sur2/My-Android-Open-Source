package com.example.userevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    protected TextView timeAMPMView;
    protected TextView timeTextView;
    protected Thread timeThread;
    protected boolean isRunning;
    protected Handler mainHandler;

    protected TextView bellTextView;
    protected TextView labelTextView;
    protected CheckBox repeatCheckView;
    protected CheckBox vibrateCheckView;
    protected Switch switchView;

    // touch 시 좌표값 저장
    protected float initX;
    protected long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                setCurrentTime();
            }
        };

        timeAMPMView = findViewById(R.id.ampm);
        timeTextView = findViewById(R.id.time);
        isRunning = true;
        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Message message = mainHandler.obtainMessage();
                        mainHandler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        timeThread.start();

        bellTextView = findViewById(R.id.bell_name);
        labelTextView = findViewById(R.id.label);
        repeatCheckView = findViewById(R.id.repeatCheck);
        vibrateCheckView = findViewById(R.id.vibrate);
        switchView = findViewById(R.id.onOff);

        bellTextView.setOnClickListener(this);
        labelTextView.setOnClickListener(this);

        repeatCheckView.setOnCheckedChangeListener(this);
        vibrateCheckView.setOnCheckedChangeListener(this);
        switchView.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 터치 닿을 때
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getX();
        }
        // 터치 땠을 때
        else if(event.getAction() == MotionEvent.ACTION_UP) {
            // 터치 닿을 때와 땠을 때 위치 차이로 판단
            float diffX = initX - event.getRawX();
            // 30px 차이 기준
            if (diffX > 30) {
                showToast("왼쪽으로 화면을 밀었습니다.");
            } else if (diffX < -30) {
                showToast("오른쪽으로 화면을 밀었습니다.");
            }
        }
        return true;
        //return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {
                showToast("종료할려면 한번 더 누르세요.");
                initTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showToast(String s) {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date curr = Calendar.getInstance().getTime();
        if (curr.getHours() >= 12) {
            timeAMPMView.setText("오후");
        } else {
            timeAMPMView.setText("오전");
        }
        timeTextView.setText(dateFormat.format(curr));
    }

    @Override
    public void onClick(View v) {
        if (v == bellTextView) {
            showToast("bell text click event...");
        } else if(v == labelTextView) {
            showToast("label text click evnet...");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == repeatCheckView) {
            showToast("repeat checkbox is " + isChecked);
        } else if(buttonView == vibrateCheckView) {
            showToast("vibrate checkbox is " + isChecked);
        } else if(buttonView == switchView) {
            showToast("switch is " + isChecked);
        }
    }

    @Override
    public void finish() {
        try {
            isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.finish();
    }
}
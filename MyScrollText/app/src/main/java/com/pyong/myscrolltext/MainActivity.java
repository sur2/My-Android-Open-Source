package com.pyong.myscrolltext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    Button addLogBtn;
    Button clearLogBtn;
    TextView logTV;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = new StringBuilder();
        logTV = findViewById(R.id.log_text_view);
        scrollView = findViewById(R.id.log_scroll_view);

        addLogBtn = findViewById(R.id.add_button);
        addLogBtn.setOnClickListener(v -> {
            LocalDateTime localDateTime = LocalDateTime.now();
            String time = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            sb.append(time);
            sb.append("\n");
            logTV.setText(sb.toString());
            scrollView.scrollTo(0, logTV.getBottom());
        });

        clearLogBtn = findViewById(R.id.clear_button);
        clearLogBtn.setOnClickListener(v -> {
            sb.setLength(0);
            logTV.setText(sb.toString());
        });

    }
}
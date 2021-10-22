package com.example.numberpickertest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

public class MyNumberPicker extends NumberPicker {
    // 생성자의 매개변수에 Context, AttributeSet 필수
    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTimeFormat();
    }

    public void setTimeFormat() {
        setFormatter(new MyFormat());
    }

    class MyFormat implements NumberPicker.Formatter {

        @Override
        public String format(int value) {
            return String.format("%02d", value);
        }
    }

}

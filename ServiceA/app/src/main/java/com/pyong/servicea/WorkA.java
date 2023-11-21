package com.pyong.servicea;

import android.util.Log;

public class WorkA {
    public static final String TAG = "TEST::WorkA";
    private static WorkA instance;

    private WorkA() {
        Log.d(TAG, "create WorkA instance " + this.toString());
    }

    public static WorkA getInstance() {
        if (instance == null)
            instance = new WorkA();
        return instance;
    }

}

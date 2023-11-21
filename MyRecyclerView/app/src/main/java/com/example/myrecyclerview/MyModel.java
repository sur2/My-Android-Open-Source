package com.example.myrecyclerview;

import android.util.Log;

import lombok.Getter;
import lombok.Setter;

public class MyModel {

    private static final String TAG = "로그";

    @Getter @Setter
    private String name = "";
    @Getter @Setter
    private String profileImage = "";

    public MyModel(String name, String profileImage) {
        this.name = name;
        this.profileImage = profileImage;

        Log.d(TAG, "MyModel - Constructor called");
    }

}

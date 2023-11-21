package com.example.myrecyclerview;

import android.app.Application;

public class App extends Application {

    private static App app = null;

    public static App getInstance() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}

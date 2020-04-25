package com.example.mvvmroomretrofit;

import android.app.Application;

public class MyApplication extends Application {
    public static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        if (application==null){
            application = new MyApplication();
        }
    }
}

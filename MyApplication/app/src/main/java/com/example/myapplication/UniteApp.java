package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.db.DBManager;

public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initDB(getApplicationContext());
    }
}

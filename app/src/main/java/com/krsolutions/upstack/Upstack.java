package com.krsolutions.upstack;

import android.app.Application;

import com.krsolutions.upstack.Database.AppDatabase;

import androidx.room.Room;

public class Upstack extends Application {
    AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.NAME).fallbackToDestructiveMigration().build();
    }
    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}

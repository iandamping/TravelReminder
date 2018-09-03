package com.example.jun.travelreminder;

import android.app.Application;
import android.content.Context;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;

import io.reactivex.disposables.Disposable;

public class MainApplication extends Application {
    public static Disposable disposable;
    public static Context CONTEXT = null;
    public static DatabaseNya mDatabase;

    public static Context get() {
        return (MainApplication) CONTEXT.getApplicationContext();
    }

    public static Disposable getDisposable() {
        return (Disposable) disposable;
    }

    public static DatabaseNya getDatabase() {
        return (DatabaseNya) mDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();
        mDatabase = DatabaseNya.getsObjectClassIni(CONTEXT);
    }
}

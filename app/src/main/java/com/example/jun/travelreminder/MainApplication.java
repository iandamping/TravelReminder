package com.example.jun.travelreminder;

import android.app.Application;
import android.content.Context;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.reactivex.disposables.CompositeDisposable;

public class MainApplication extends Application {
    public static final String DATE_FORMAT = "dd/MM/yyy";
    public static CompositeDisposable disposable;
    public static Context CONTEXT = null;
    public static DatabaseNya mDatabase;
    public static DateFormat dateFormat;

    public static Context get() {
        return (MainApplication) CONTEXT.getApplicationContext();
    }

    public static CompositeDisposable getDisposable() {
        return (CompositeDisposable) disposable;
    }

    public static DatabaseNya getDatabase() {
        return (DatabaseNya) mDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();
        mDatabase = DatabaseNya.getsObjectClassIni(CONTEXT);
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
    }
}

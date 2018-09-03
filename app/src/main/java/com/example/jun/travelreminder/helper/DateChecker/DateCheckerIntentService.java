package com.example.jun.travelreminder.helper.DateChecker;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class DateCheckerIntentService extends IntentService {

    public DateCheckerIntentService() {
        super("DateChecker");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        DateChecker.dailyCheck(this);
    }
}

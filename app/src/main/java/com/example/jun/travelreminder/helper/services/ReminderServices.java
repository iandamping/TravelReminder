package com.example.jun.travelreminder.helper.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.jun.travelreminder.helper.DateChecker.DateChecker;
import com.example.jun.travelreminder.helper.Notification.ReminderTasks;

public class ReminderServices extends IntentService {

    public ReminderServices() {
        super("ReminderNotif");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        ReminderTasks.executeTask(this, action);
        DateChecker.dailyCheck(this);
    }
}

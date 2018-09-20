package com.example.jun.travelreminder.helper.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jun.travelreminder.helper.Notification.ReminderTasks;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ReminderTasks.executeTask(context, ReminderTasks.ACTION_REMINDER_NOTIF);

    }

}

package com.example.jun.travelreminder.helper.Notification;

import android.content.Context;

public class ReminderTasks {
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String ACTION_REMINDER_NOTIF = "reminder-notif";

    public static void executeTask(Context context, String action) {
        if (ACTION_REMINDER_NOTIF.equals(action)) {
            issueChargingReminder(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtil.clearAllNotification(context);
        }
    }

    private static void issueChargingReminder(Context context) {
        NotificationUtil.reminderNotif(context);
    }
}

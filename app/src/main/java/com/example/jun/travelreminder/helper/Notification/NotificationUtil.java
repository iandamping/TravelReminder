package com.example.jun.travelreminder.helper.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.jun.travelreminder.MainActivity;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.helper.services.ReminderServices;


public class NotificationUtil {
    private static final String TAG = NotificationUtil.class.getName();
    private static final String NOTIFICATION = "notification";
    private static final int REMINDER_NOTIFICATION_ID = 1138;
    private static final String OREO_NOTIF_CHANEL_ID = "oreo-notif";
    private static final int PENDING_INTENT_ID = 3417;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    public static void reminderNotif(Context context) {
        NotificationManager notif = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //khusus versi Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(OREO_NOTIF_CHANEL_ID, context.getString(R.string.oreo_notif), NotificationManager.IMPORTANCE_HIGH);
            notif.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, OREO_NOTIF_CHANEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.img_indonesia)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.oreo_notif_tittle))
                .setContentText(context.getString(R.string.oreo_notif_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.oreo_notif_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .addAction(ignoreNotification(context))
                .setContentIntent(contentIntent(context))  //ini pendingIntent
                .setAutoCancel(true);  //notif akan hilang setelah di klik

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notif.notify(REMINDER_NOTIFICATION_ID, builder.build());

    }


    private static PendingIntent contentIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, PENDING_INTENT_ID, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        return BitmapFactory.decodeResource(res, R.drawable.squirrel_icon);
    }

    public static void clearAllNotification(Context context) {
        NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancelAll();
    }

    private static NotificationCompat.Action ignoreNotification(Context context) {
        Intent i = new Intent(context, ReminderServices.class);
        i.setAction(ReminderTasks.ACTION_DISMISS_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getService(context, ACTION_IGNORE_PENDING_INTENT_ID, i, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Action(R.drawable.ic_cancel_black_24dp,
                "No, Thanks !", pendingIntent);
    }

}

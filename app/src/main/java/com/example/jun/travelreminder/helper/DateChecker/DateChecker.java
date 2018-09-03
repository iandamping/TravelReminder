package com.example.jun.travelreminder.helper.DateChecker;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.jun.travelreminder.Databasenya.ROOM.AppExecutor;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.helper.Notification.NotificationUtil;
import com.example.jun.travelreminder.helper.Notification.ReminderTasks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateChecker {
    private static final String TAG = DateChecker.class.getName();
    private static final String DATE_FORMAT = "dd/MM/yyy";
    public static boolean status = false;
    private static List<DateUsers> testing;
    private static Date dateDatabase = new Date();
    private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static String databaseDate = "";
    private static String currentDate = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(new Date());

    public static void dailyCheck(final Context context) {
        testing = new ArrayList<>();
        AppExecutor.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                testing = MainApplication.getDatabase().dao_date().load_all_date_test();
                for (int i = 0; i < testing.size(); i++) {
                    dateDatabase = testing.get(i).getArrivaldate();
                    databaseDate = dateFormat.format(dateDatabase);
                    if (databaseDate.equalsIgnoreCase(currentDate)) {
                        status = true;
                        Log.d(TAG, status + "--> ini statusnya");
                        //todo hanya mengambil status TRUE / FALSE saja
//                        NotificationUtil.reminderNotif(context);
//                        ReminderTasks.executeTask(context, ReminderTasks.ACTION_REMINDER_NOTIF);
                    }
                }
            }
        });
    }
}

//        LiveData<List<DateUsers>> date_user = mDb.dao_date().load_all_date();
//        date_user.observe((LifecycleOwner) DateChecker.this, new Observer<List<DateUsers>>() {
//            @Override
//            public void onChanged(@Nullable List<DateUsers> dateUsers) {
//                for (DateUsers dates : dateUsers){
//                    dateDatabase = dates.getArrivaldate();
//                    databaseDate = dateFormat.format(dateDatabase);
//                    if (databaseDate.equalsIgnoreCase(currentDate)){
//                        status = true;
//                    }else return;
//                }
//            }
//        });
//    }


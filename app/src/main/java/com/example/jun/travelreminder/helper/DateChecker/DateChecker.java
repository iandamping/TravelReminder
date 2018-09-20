package com.example.jun.travelreminder.helper.DateChecker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.helper.alarm.AlarmReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.content.Context.ALARM_SERVICE;

public class DateChecker {
    private static Calendar calendar = Calendar.getInstance();
    private static AlarmManager am;
    private static PendingIntent pi;
    private static String databaseDate = "";
    private static String currentDate = new SimpleDateFormat(MainApplication.DATE_FORMAT, Locale.getDefault()).format(new Date());


    public static void dailyCheck(final Context context) {
        MainApplication.getDisposable().add(MainApplication.getDatabase().dao_travelItem().loadFlowabale()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> Observable.fromIterable(results).subscribe(new DisposableObserver<item>() {
                    @Override
                    public void onNext(item dateUsers) {
                        databaseDate = MainApplication.dateFormat.format(dateUsers.getArrivaldate());
                        if (databaseDate.equalsIgnoreCase(currentDate)) {
                            calendar.set(Calendar.HOUR_OF_DAY, dateUsers.getSelectedHour());
                            calendar.set(Calendar.MINUTE, dateUsers.getSelectedMin());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Intent i = new Intent(context, AlarmReceiver.class);
                        pi = PendingIntent.getBroadcast(context.getApplicationContext(), 0, i, FLAG_CANCEL_CURRENT);
                        am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 0, pi);
                    }
                })));

    }
}




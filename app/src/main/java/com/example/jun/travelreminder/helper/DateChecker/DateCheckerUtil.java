package com.example.jun.travelreminder.helper.DateChecker;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class DateCheckerUtil {
    private static final int SYNC_INTERVAL_HOURS = 12;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;
    private static final String REMINDER_JOB_TAG = "sync-job-tag";
    private static final String TAG = DateCheckerUtil.class.getName();
    private static final int REMINDER_INTERVAL_MINUTES = 1;
    private static final int REMINDER_INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES));
    private static final int SYNC_SECONDS = REMINDER_INTERVAL_SECONDS;
    private static boolean sInitialized;

    synchronized public static void scheduleDateCheck(Context context) {
        if (sInitialized) return;
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job myJob = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(FirebaseDateChecker.class)
                // uniquely identifies the job
                .setTag(REMINDER_JOB_TAG)
                // one-off job
                .setRecurring(false)
                // don't persist past a device reboot
                .setLifetime(Lifetime.FOREVER)
                // start between 0 and 15 minutes (900 seconds)
                .setTrigger(Trigger.executionWindow(REMINDER_INTERVAL_SECONDS, SYNC_SECONDS + REMINDER_INTERVAL_SECONDS))
                // overwrite an existing job with the same tag
                .setReplaceCurrent(true)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setConstraints(
                        Constraint.ON_ANY_NETWORK
//                        , Constraint.DEVICE_CHARGING
                )
                .build();
        dispatcher.schedule(myJob);
        Log.d(TAG, "START CHECKING");
        sInitialized = true;
    }
}

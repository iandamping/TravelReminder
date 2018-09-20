package com.example.jun.travelreminder.helper.Notification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.jun.travelreminder.helper.DateChecker.DateChecker;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class NotificationJobService extends JobService {
    private AsyncTask mBackgroundTask;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = getApplicationContext();
                DateChecker.dailyCheck(context);
//                if (DateChecker.status == true) {
//                    ReminderTasks.executeTask(context, ReminderTasks.ACTION_REMINDER_NOTIF);
//                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}

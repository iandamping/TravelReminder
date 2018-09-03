package com.example.jun.travelreminder.helper.DateChecker;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class FirebaseDateChecker extends JobService {
    private AsyncTask asyncDate;

    @Override
    public boolean onStartJob(final JobParameters job) {
        asyncDate = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = getApplicationContext();
//                DateChecker.dailyCheck(context);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
            }
        };
        asyncDate.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}

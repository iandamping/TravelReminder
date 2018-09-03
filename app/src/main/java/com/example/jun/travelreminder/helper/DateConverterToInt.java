package com.example.jun.travelreminder.helper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverterToInt {
    private static final String DATE_FORMAT = "dd/MM/yyy";

    public static int getDifference(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            date = sdf.parse(DATE_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        long time = c.getTimeInMillis();
        long curr = System.currentTimeMillis();
        long diff = curr - time;//Time difference in milliseconds
        diff = diff / 1000;
        int neededTime = (int) diff;
        Log.d("jumlah tangal", Integer.toString(neededTime));
        return neededTime;

    }
}

package com.example.jun.travelreminder.Databasenya.ROOM;


import android.arch.persistence.room.TypeConverter;

import java.util.Date;


public class date_converter {
    /*
    convert dari tipe data LONG ke tipe data DATE
     */
    @TypeConverter
    public Date fromTimeStamp(Long values) {
        return values == null ? null : new Date(values);
    }

    /*
    convert dari DATE ke tipe data LONG
     */
    @TypeConverter
    public Long dateToTimeStamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}

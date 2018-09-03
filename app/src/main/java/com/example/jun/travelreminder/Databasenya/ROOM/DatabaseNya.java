package com.example.jun.travelreminder.Databasenya.ROOM;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.example.jun.travelreminder.Databasenya.ROOM.DAOs.DAO_date;
import com.example.jun.travelreminder.Databasenya.ROOM.DAOs.DAO_news;
import com.example.jun.travelreminder.Databasenya.ROOM.DAOs.DAO_roomnya;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.model.news.Article;

@Database(entities = {item.class, DateUsers.class, Article.class}, version = 1, exportSchema = false)
@TypeConverters({list_converter.class})
public abstract class DatabaseNya extends RoomDatabase {
    private static final String LOG_TAG = DatabaseNya.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String nama_Database = "Database Travel";
    private static DatabaseNya sObjectClassIni;

    public static DatabaseNya getsObjectClassIni(Context context) {
        if (sObjectClassIni == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Buat Database nya");
                sObjectClassIni = Room.databaseBuilder(context.getApplicationContext(),
                        DatabaseNya.class, DatabaseNya.nama_Database)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Ngambil Database ");
        return sObjectClassIni;
    }

    public abstract DAO_roomnya dao_item();

    public abstract DAO_date dao_date();

    public abstract DAO_news dao_news();
}
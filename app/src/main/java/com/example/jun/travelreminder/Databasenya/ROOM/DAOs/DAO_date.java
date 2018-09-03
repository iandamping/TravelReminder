package com.example.jun.travelreminder.Databasenya.ROOM.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;

import java.util.List;

@Dao
public interface DAO_date {

    @Query("SELECT * FROM date_users")
    LiveData<List<DateUsers>> load_all_date();

    @Query("SELECT * FROM date_users WHERE ID_date = :id")
    LiveData<DateUsers> load_by_id_date(int id);

    @Insert
    void insertDataDate(DateUsers insert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDataDate(DateUsers update);

    @Delete
    void deleteDataDate(DateUsers delete);

    @Query("SELECT * FROM date_users")
    List<DateUsers> load_all_date_test();
}

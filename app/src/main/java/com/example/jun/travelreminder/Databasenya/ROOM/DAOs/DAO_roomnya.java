package com.example.jun.travelreminder.Databasenya.ROOM.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;

import java.util.List;

@Dao
public interface DAO_roomnya {


    @Query("SELECT * FROM user_belonging")
    LiveData<List<item>> loadAllData();

    @Query("SELECT * FROM user_belonging WHERE ID_barang = :id")
    LiveData<item> loadAllById(int id);


    @Insert
    void insertAllData(item item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(item Belajar);

    @Query("DELETE FROM user_belonging")
    void deleteAll();

    @Query("SELECT * FROM user_belonging WHERE ID_barang = :id")
    item deleteAllById(int id);
}

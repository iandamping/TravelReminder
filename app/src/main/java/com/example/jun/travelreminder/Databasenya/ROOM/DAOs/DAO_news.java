package com.example.jun.travelreminder.Databasenya.ROOM.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jun.travelreminder.model.news.Article;

import java.util.List;

@Dao
public interface DAO_news {

    @Query("SELECT * FROM news_data")
    LiveData<List<Article>> load_all_news();

    @Query("SELECT * FROM news_data")
    List<Article> load_all_news_list();

    @Query("SELECT * FROM news_data WHERE ID = :id")
    LiveData<Article> load_by_id_news(int id);

    @Insert
    void insertDataNews(List<Article> insert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDataNews(Article update);

    @Delete
    void deleteDataNews(Article insert);

    @Query("DELETE FROM news_data")
    void deleteAllData();
}

package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.newsviewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.model.news.Article;

public class LoadDataNewsByID extends ViewModel {
    private LiveData<Article> articleLiveData;

    public LoadDataNewsByID(DatabaseNya db, int position_id) {
        articleLiveData = db.dao_news().load_by_id_news(position_id);
    }

    public LiveData<Article> getArticleLiveData() {
        return articleLiveData;
    }
}

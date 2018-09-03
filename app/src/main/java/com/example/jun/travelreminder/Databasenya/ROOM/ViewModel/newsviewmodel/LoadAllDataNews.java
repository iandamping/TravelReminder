package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.newsviewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.model.news.Article;

import java.util.List;

public class LoadAllDataNews extends AndroidViewModel {
    private final String TAG = LoadAllDataNews.class.getSimpleName();
    LiveData<List<Article>> listLiveData;

    public LoadAllDataNews(@NonNull Application application) {
        super(application);
        DatabaseNya mDb = DatabaseNya.getsObjectClassIni(MainApplication.CONTEXT);
        listLiveData = mDb.dao_news().load_all_news();
    }


    public LiveData<List<Article>> getListLiveData() {
        return listLiveData;
    }
}

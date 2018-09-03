package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.newsviewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;

public class addNewsViewModel extends ViewModelProvider.NewInstanceFactory {
    DatabaseNya database;
    int taskID;

    public addNewsViewModel(DatabaseNya database, int taskID) {
        this.database = database;
        this.taskID = taskID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoadDataNewsByID(database, taskID);
    }
}

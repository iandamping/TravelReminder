package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.dateviewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;

public class addDateUserViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    DatabaseNya appData;
    int tasksId;

    public addDateUserViewModelFactory(DatabaseNya appData, int tasksId) {
        this.appData = appData;
        this.tasksId = tasksId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoadDateUserViewModelbyID(appData, tasksId);
    }
}

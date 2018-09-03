package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    DatabaseNya appData;
    int tasksId;

    public AddTaskViewModelFactory(DatabaseNya appData, int tasksId) {
        this.appData = appData;
        this.tasksId = tasksId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(appData, tasksId);
    }
}

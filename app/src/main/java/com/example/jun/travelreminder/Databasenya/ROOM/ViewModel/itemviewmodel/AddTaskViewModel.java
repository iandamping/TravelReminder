package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;

public class AddTaskViewModel extends ViewModel {
    private LiveData<item> addTask;

    public AddTaskViewModel(DatabaseNya db, int taskId) {
        addTask = db.dao_item().loadAllById(taskId);
    }

    public LiveData<item> getAddTask() {
        return addTask;
    }
}

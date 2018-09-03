package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.dateviewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;

public class LoadDateUserViewModelbyID extends ViewModel {
    private LiveData<DateUsers> data;

    public LoadDateUserViewModelbyID(DatabaseNya db, int taskId) {
        data = db.dao_date().load_by_id_date(taskId);
    }

    public LiveData<DateUsers> getData() {
        return data;
    }
}

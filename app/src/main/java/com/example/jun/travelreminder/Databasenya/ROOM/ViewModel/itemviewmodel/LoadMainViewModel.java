package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;

import java.util.List;

public class LoadMainViewModel extends AndroidViewModel {
    private final String TAG = LoadMainViewModel.class.getSimpleName();
    private LiveData<List<item>> tasks;

    public LoadMainViewModel(@NonNull Application application) {
        super(application);
        DatabaseNya data = DatabaseNya.getsObjectClassIni(this.getApplication());
        tasks = data.dao_travelItem().loadAllData();

        //load semua barang datanya sudah bukan ListData lagi tapi LiveData
    }

    public LiveData<List<item>> getTasks() {
        return tasks;
    }
}

package com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.dateviewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;

import java.util.List;

public class DateUserViewModel extends AndroidViewModel {
    private final String TAG = DateUserViewModel.class.getSimpleName();
    private LiveData<List<DateUsers>> date;

    public DateUserViewModel(@NonNull Application application) {
        super(application);
        DatabaseNya data = DatabaseNya.getsObjectClassIni(this.getApplication());
        date = data.dao_date().load_all_date();
    }

    public LiveData<List<DateUsers>> getDate() {
        return date;
    }
}

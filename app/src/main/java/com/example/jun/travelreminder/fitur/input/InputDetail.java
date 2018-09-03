package com.example.jun.travelreminder.fitur.input;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.dateviewmodel.LoadDateUserViewModelbyID;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.dateviewmodel.addDateUserViewModelFactory;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModel;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModelFactory;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputDetail extends AppCompatActivity implements InputView {
    public static final String EXTRA_TASK_ID_KEY = "extra_key";
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private static final int DEFAULT_TASK_ID = -1;
    private TextView tvTujuan, tvDepart, tvArrival, tvBarang;
    private DatabaseNya db;
    private FloatingActionButton fabEdit;
    private static final int mEditDataId = 2;
    private int mTaskId = DEFAULT_TASK_ID;
    private InputPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_output_detail);
        presenter = new InputPresenter(this);
        presenter.onCreate(this);
    }

    @Override
    public void showData(item item_list) {

        tvTujuan.setText(item_list.getDestination());

        tvBarang.append(item_list.getItem());

    }

    @Override
    public void showDate(DateUsers dateUsers) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String ArrivalDate = df.format(dateUsers.getArrivaldate());
        String DepartDate = df.format(dateUsers.getDepartureDate());
        tvArrival.setText(ArrivalDate);
        tvDepart.setText(DepartDate);
    }

    @Override
    public void initView() {
        fabEdit = findViewById(R.id.fabEdit);
        tvTujuan = (TextView) findViewById(R.id.tvTujuan);
        tvDepart = (TextView) findViewById(R.id.tvDepart);
        tvArrival = (TextView) findViewById(R.id.tvArrival);
        tvBarang = (TextView) findViewById(R.id.tvBarang);
        db = DatabaseNya.getsObjectClassIni(getApplicationContext());

        Intent i = getIntent();
        if (i != null && i.hasExtra(EXTRA_TASK_ID_KEY)) {
            if (mTaskId == DEFAULT_TASK_ID) {
                mTaskId = i.getIntExtra(EXTRA_TASK_ID_KEY, DEFAULT_TASK_ID);
                AddTaskViewModelFactory addTaskViewModelF = new AddTaskViewModelFactory(db, mTaskId);

                final AddTaskViewModel addTaskViewModel = ViewModelProviders.of(this, addTaskViewModelF).get(AddTaskViewModel.class);
                addTaskViewModel.getAddTask().observe(this, new Observer<item>() {
                    @Override
                    public void onChanged(@Nullable item semua) {
                        addTaskViewModel.getAddTask().removeObserver(this);
                        showData(semua);
                    }
                });
                addDateUserViewModelFactory dateFactory = new addDateUserViewModelFactory(MainApplication.getDatabase(), mTaskId);
                final LoadDateUserViewModelbyID dateViewModel = ViewModelProviders.of(this, dateFactory).get(LoadDateUserViewModelbyID.class);
                dateViewModel.getData().observe(this, new Observer<DateUsers>() {
                    @Override
                    public void onChanged(@Nullable DateUsers dateUsers) {
                        dateViewModel.getData().removeObserver(this);
                        showDate(dateUsers);
                    }
                });
            }
        }
    }

    @Override
    public void initListener() {
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputDetail.this, InputActivity.class);
                intent.putExtra(InputActivity.EXTRA_TASK_ID_KEY, mEditDataId);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void startTask() {

    }

    @Override
    public void finishedTask() {

    }

    @Override
    public void failureTask(String message) {

    }
}

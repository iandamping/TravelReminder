package com.example.jun.travelreminder.fitur.input;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModel;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModelFactory;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InputDetail extends AppCompatActivity implements InputView {
    public static final String EXTRA_TASK_ID_KEY = "extra_key";
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private static final int DEFAULT_TASK_ID = -1;
    private static final int mEditDataId = 2;
    private TextView tvTujuan, tvDepart, tvArrival, tvBarang;
    private FloatingActionButton fabEdit;
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

        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String ArrivalDate = df.format(item_list.getArrivaldate());
        String DepartDate = df.format(item_list.getDepartureDate());
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

        Intent i = getIntent();
        if (i != null && i.hasExtra(EXTRA_TASK_ID_KEY)) {
            if (mTaskId == DEFAULT_TASK_ID) {
                mTaskId = i.getIntExtra(EXTRA_TASK_ID_KEY, DEFAULT_TASK_ID);
                AddTaskViewModelFactory addTaskViewModelF = new AddTaskViewModelFactory(MainApplication.getDatabase(), mTaskId);

                final AddTaskViewModel addTaskViewModel = ViewModelProviders.of(this, addTaskViewModelF).get(AddTaskViewModel.class);
                addTaskViewModel.getAddTask().observe(this, new Observer<item>() {
                    @Override
                    public void onChanged(@Nullable item semua) {
                        addTaskViewModel.getAddTask().removeObserver(this);
                        showData(semua);
                    }
                });
            }
        }
    }

    @Override
    public void initListener() {
        fabEdit.setOnClickListener(view -> {
            Intent intent = new Intent(InputDetail.this, InputActivity.class);
            intent.putExtra(InputActivity.EXTRA_TASK_ID_KEY, mEditDataId);
            startActivity(intent);
            finish();
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

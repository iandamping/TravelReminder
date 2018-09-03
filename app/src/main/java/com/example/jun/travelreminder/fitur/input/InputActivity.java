package com.example.jun.travelreminder.fitur.input;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jun.travelreminder.Databasenya.ROOM.AppExecutor;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.dateviewmodel.LoadDateUserViewModelbyID;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.dateviewmodel.addDateUserViewModelFactory;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModel;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModelFactory;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.helper.PreferenceHelper;
import com.example.jun.travelreminder.helper.ValidateHelper;
import com.example.jun.travelreminder.model.bola.Bola;
import com.example.jun.travelreminder.network.RequestObserver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.observers.DisposableObserver;


public class InputActivity extends AppCompatActivity implements InputView {
    public static final String EXTRA_TASK_ID_KEY = "extra_key";
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    private static final int DEFAULT_TASK_ID = -1;
    final Calendar cal = Calendar.getInstance();
    PreferenceHelper pref;
    private EditText etBarang, etDepartDate, etArrivalDate, etDestination;
    private Button btnSave;
    private InputPresenter presenter;
    private int mTaskId = DEFAULT_TASK_ID;

    public static void start(Context context) {
        Intent intent = new Intent(context, InputActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = new PreferenceHelper(this);
        setContentView(R.layout.input_item_layout);
        presenter = new InputPresenter(this);
        presenter.onCreate(this);
    }

    @Override
    public void showData(item item_list) {
        //todo ini buat edit barang
        if (item_list == null) {
            return;
        }
        etBarang.setText(item_list.getItem());
        etDestination.setText(item_list.getDestination());
    }

    @Override
    public void showDate(DateUsers dateUsers) {
        //todo ini buat edit barang
        if (dateUsers == null) {
            return;
        }
        String arrivalDate = dateFormat.format(dateUsers.getArrivaldate());
        String departDate = dateFormat.format(dateUsers.getDepartureDate());
        etArrivalDate.setText(arrivalDate);
        etDepartDate.setText(departDate);
    }

    @Override
    public void initView() {
        etBarang = (EditText) findViewById(R.id.etBarang);
        btnSave = (Button) findViewById(R.id.btnSave);
        etDepartDate = (EditText) findViewById(R.id.etDepartDates);
        etArrivalDate = (EditText) findViewById(R.id.etArrivalDates);
        etDestination = (EditText) findViewById(R.id.etDestination);

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
        etDepartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDeparture(etDepartDate);
            }
        });
        etArrivalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickArivalDate(etArrivalDate);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ValidateHelper.validate(MainApplication.CONTEXT, etBarang, ValidateHelper.Type.EMPTY)) {
                    return;
                }
                if (!ValidateHelper.validate(MainApplication.CONTEXT, etArrivalDate, ValidateHelper.Type.EMPTY)) {
                    return;
                }
                if (!ValidateHelper.validate(MainApplication.CONTEXT, etDepartDate, ValidateHelper.Type.EMPTY)) {
                    return;
                }
                startTask();
                Toast.makeText(getApplicationContext(), "berhasil disimpan", Toast.LENGTH_SHORT).show();
                etBarang.setText("");
                etArrivalDate.setText("");
                etDepartDate.setText("");
                etDestination.setText("");
                finish();
            }

        });

    }

    @Override
    public void startTask() {
        Date Arrivaldate = new Date();
        Date DepartureDate = new Date();
        String namaBarang = etBarang.getText().toString().trim();
        String tujuan = etDestination.getText().toString().trim();
        String tanggalKeberangkatan = etDepartDate.getText().toString().trim();
        String tanggalKedatangan = etArrivalDate.getText().toString().trim();

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            DepartureDate = dateFormat.parse(tanggalKeberangkatan);
            Arrivaldate = dateFormat.parse(tanggalKedatangan);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final item objBelajarRoom = new item(namaBarang, tujuan);
        AppExecutor.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mTaskId == DEFAULT_TASK_ID) {
                    MainApplication.getDatabase().dao_item().insertAllData(objBelajarRoom);
                } else {
                    objBelajarRoom.setIDnya(mTaskId);
                    MainApplication.getDatabase().dao_item().updateAll(objBelajarRoom);
                }
            }
        });

        final DateUsers objDate = new DateUsers(DepartureDate, Arrivaldate);
        AppExecutor.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                MainApplication.getDatabase().dao_date().insertDataDate(objDate);
            }
        });

    }

    /*
    method ini memangil datePickerDialog yang di set khusus ketika ET di klik
    menggunakan getStringFormateDate method utk pengisian format nya
     */
    public void pickArivalDate(EditText etOnFocus) {
        final int years = cal.get(Calendar.YEAR);
        final int months = cal.get(Calendar.MONTH);
        final int days = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etOnFocus.setText(presenter.getFormatedDate(year, monthOfYear, dayOfMonth));
                    }
                }, years, months, days);
        datePicker.show();
    }

    public void pickDeparture(EditText etOnFocus) {
        final int years = cal.get(Calendar.YEAR);
        final int months = cal.get(Calendar.MONTH);
        final int days = cal.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etDepartDate.setText(presenter.getFormatedDate(year, monthOfYear, dayOfMonth));
                    }
                }, years, months, days);
        datePicker.show();
    }

    @Override
    public void finishedTask() {

    }

    @Override
    public void failureTask(String message) {

    }


}






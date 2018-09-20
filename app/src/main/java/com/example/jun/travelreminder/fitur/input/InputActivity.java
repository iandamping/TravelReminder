package com.example.jun.travelreminder.fitur.input;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jun.travelreminder.Databasenya.ROOM.AppExecutor;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModel;
import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.AddTaskViewModelFactory;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.helper.ValidateHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;


public class InputActivity extends AppCompatActivity implements InputView {
    public static final String EXTRA_TASK_ID_KEY = "extra_key";
    private static final int DEFAULT_TASK_ID = -1;
    final Calendar cal = Calendar.getInstance();
    String tanggalKeberangkatan, tanggalKedatangan;
    private RadioButton rbTimePicker;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(MainApplication.DATE_FORMAT, Locale.getDefault());
    private EditText etBarang, etDepartDate, etArrivalDate, etDestination, etHours;
    private Button btnSave;
    private InputPresenter presenter;
    private int mTaskId = DEFAULT_TASK_ID;
    private int Hours, Minute;
    private boolean checkTime = false;


    public static void start(Context context) {
        Intent intent = new Intent(context, InputActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        String arrivalDate = dateFormat.format(item_list.getArrivaldate());
        String departDate = dateFormat.format(item_list.getDepartureDate());
        etArrivalDate.setText(arrivalDate);
        etDepartDate.setText(departDate);
    }


    @Override
    public void initView() {
        etHours = findViewById(R.id.etHours);
        rbTimePicker = findViewById(R.id.rbTimePicker);
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
//                addDateUserViewModelFactory dateFactory = new addDateUserViewModelFactory(MainApplication.getDatabase(), mTaskId);
//                final LoadDateUserViewModelbyID dateViewModel = ViewModelProviders.of(this, dateFactory).get(LoadDateUserViewModelbyID.class);
//                dateViewModel.getData().observe(this, new Observer<DateUsers>() {
//                    @Override
//                    public void onChanged(@Nullable DateUsers dateUsers) {
//                        dateViewModel.getData().removeObserver(this);
//                        showDate(dateUsers);
//                    }
//                });
            }
        }
    }


    @Override
    public void initListener() {
        etDepartDate.setOnClickListener(view -> pickDeparture(etDepartDate));
        etArrivalDate.setOnClickListener(view -> pickArivalDate(etArrivalDate));


        btnSave.setOnClickListener(view -> {
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
        });
        rbTimePicker.setOnClickListener(view -> {
            if (!checkTime) {
                rbTimePicker.setChecked(true);
                checkTime = true;
                Calendar mcurrentTime = Calendar.getInstance();
                final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                final int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(InputActivity.this, (timePicker, selectedHour, selectedMinute) -> {
                    Observable.just(selectedHour).subscribe(results -> Hours = results);
                    Observable.just(selectedMinute).subscribe(results -> Minute = results);
                    etHours.setText(selectedHour + ":" + selectedMinute);
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        checkTime = false;
                        rbTimePicker.setChecked(false);
                        mTimePicker.dismiss();
                    }
                });

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            } else {
                rbTimePicker.setChecked(false);
                checkTime = false;
                etHours.setText("");

            }

        });

    }

    @Override
    public void startTask() {
        Date Arrivaldate = new Date();
        Date DepartureDate = new Date();
        String namaBarang = etBarang.getText().toString().trim();
        String tujuan = etDestination.getText().toString().trim();

        try {
            DepartureDate = dateFormat.parse(tanggalKeberangkatan);
            Arrivaldate = dateFormat.parse(tanggalKedatangan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        final DateUsers objDate = new DateUsers(DepartureDate, Arrivaldate, Hours, Minute);
        final item objBelajarRoom = new item(namaBarang, tujuan, DepartureDate, Arrivaldate, Hours, Minute);
        AppExecutor.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mTaskId == DEFAULT_TASK_ID) {
                    MainApplication.getDatabase().dao_travelItem().insertAllData(objBelajarRoom);
//                    MainApplication.getDatabase().dao_date().insertDataDate(objDate);
                } else {
                    objBelajarRoom.setIDnya(mTaskId);
                    MainApplication.getDatabase().dao_travelItem().updateAll(objBelajarRoom);
                }
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
                (datePicker1, year, monthOfYear, dayOfMonth) -> {
                    etOnFocus.setText(presenter.getFormatedDate(year, monthOfYear, dayOfMonth));
                    Observable.just(etOnFocus.getText().toString().trim())
                            .subscribe(results -> tanggalKedatangan = results);
                }, years, months, days);
        datePicker.show();
    }

    public void pickDeparture(EditText etOnFocus) {
        final int years = cal.get(Calendar.YEAR);
        final int months = cal.get(Calendar.MONTH);
        final int days = cal.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePicker = new DatePickerDialog(this,
                (datePicker1, year, monthOfYear, dayOfMonth) -> {
                    etOnFocus.setText(presenter.getFormatedDate(year, monthOfYear, dayOfMonth));
                    Observable.just(etDepartDate.getText().toString().trim())
                            .subscribe(results -> tanggalKeberangkatan = results);
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






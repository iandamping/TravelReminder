package com.example.jun.travelreminder.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void startTask() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    public void finishedTask() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void failureTask(String message) {
        progressDialog.dismiss();
    }
}

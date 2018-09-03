package com.example.jun.travelreminder.fitur.input;

import android.content.Context;

import com.example.jun.travelreminder.base.BasePresenter;

public class InputPresenter implements BasePresenter {
    private InputView mView;
    private Context context;

    public InputPresenter(InputView mView) {
        this.mView = mView;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(Context context) {
        this.context = context;
        mView.initView();
        mView.initListener();
    }

    @Override
    public void onPause() {

    }

    public String getFormatedDate(int years, int months, int dayOfMonth) {
        int month = months + 1;
        String formatMonth = "" + month;
        String formatDay = "" + dayOfMonth;
        if (month < 10) {
            formatMonth = "0" + month;
        }
        if (dayOfMonth < 10) {
            formatDay = "0" + dayOfMonth;
        }
        return formatDay + "-" + String.valueOf(formatMonth) + "-" + String.valueOf(years);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        mView = null;
        context = null;
    }

}

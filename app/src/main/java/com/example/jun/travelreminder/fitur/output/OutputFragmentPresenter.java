package com.example.jun.travelreminder.fitur.output;

import android.content.Context;
import android.view.View;

import com.example.jun.travelreminder.base.BaseFragmentPresenter;

import io.reactivex.disposables.Disposable;

public class OutputFragmentPresenter implements BaseFragmentPresenter {
    private Context context;
    private OutputFragmentView mView;

    public OutputFragmentPresenter(OutputFragmentView mView) {
        this.mView = mView;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;

    }

    @Override
    public void onCreateView(View view) {
        mView.initView(view);
        mView.initListener();
    }

    @Override
    public void onCompletedPull(Disposable d) {

    }


}

package com.example.jun.travelreminder.fitur.news;

import android.content.Context;
import android.view.View;

import com.example.jun.travelreminder.base.BaseFragmentPresenter;
import com.example.jun.travelreminder.network.ApiKey;
import com.example.jun.travelreminder.network.RequestObserver;

import io.reactivex.disposables.Disposable;

public class NewsFragmentPresenter implements BaseFragmentPresenter {
    public Disposable disposable;
    private Context context;
    private NewsFragmentView mView;

    public NewsFragmentPresenter(NewsFragmentView mView) {
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
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void getData(String action) {
        RequestObserver.getUser(action, ApiKey.ApiSource, ApiKey.ApiKeyNews);

    }

}

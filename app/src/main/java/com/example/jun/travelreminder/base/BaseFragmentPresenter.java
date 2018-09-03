package com.example.jun.travelreminder.base;

import android.content.Context;
import android.view.View;

import io.reactivex.disposables.Disposable;

public interface BaseFragmentPresenter {
    Context getContext();

    void onAttach(Context context);

    void onCreateView(View view);

    void onCompletedPull(Disposable d);
}

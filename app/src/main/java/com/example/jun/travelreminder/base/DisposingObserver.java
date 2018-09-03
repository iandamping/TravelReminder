package com.example.jun.travelreminder.base;

import android.support.annotation.CallSuper;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class DisposingObserver<T> implements Observer<T> {
    @Override
    @CallSuper
    public void onSubscribe(Disposable d) {
        DisposableManager.add(d);
    }


    @Override
    public void onNext(T next) {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
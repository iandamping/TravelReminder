package com.example.jun.travelreminder.base;

public interface BaseView {
    void initView();

    void initListener();

    void startTask();

    void finishedTask();

    void failureTask(String message);
}

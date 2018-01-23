package com.apphouse.businessscheduler.main;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import static android.support.v4.util.Preconditions.checkNotNull;


public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;

    @SuppressLint("RestrictedApi")
    public MainPresenter(@NonNull MainContract.View mainView) {
        this.mainView = checkNotNull(mainView, "tasksView cannot be null!");
        mainView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onClick() {
        mainView.showToast();
    }
}

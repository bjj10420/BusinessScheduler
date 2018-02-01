package com.apphouse.businessscheduler.main;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static android.support.v4.util.Preconditions.checkNotNull;


public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;
    private String selectedDate;


    @SuppressLint("RestrictedApi")
    public MainPresenter(@NonNull MainContract.View mainView) {
        this.mainView = checkNotNull(mainView, "tasksView cannot be null!");
        mainView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void goToAddPageOnAddBtnClicked() {

    }

    @Override
    public void setSelectedDateData(CalendarDay date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        selectedDate = formatter.format(date.getDate());
        Log.d("onDateSelected 테스트", selectedDate);
    }

    @Override
    public void reloadCurrentPageData() {

    }


    @Override
    public void goToDetailPageOnItemSelected() {

    }

    @Override
    public void refresh() {

    }
}

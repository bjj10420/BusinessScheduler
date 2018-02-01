package com.apphouse.businessscheduler.main;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
    public void goToAddPageOnAddBtnClicked() {

    }

    @Override
    public void setSelectedDateData() {

    }

    @Override
    public void reloadCurrentPageData() {

    }

    @Override
    public void onDateSelected(CalendarDay date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String dateString = formatter.format(date.getDate());
        Log.d("onDateSelected 테스트", dateString);
    }

    @Override
    public void goToDetailPageOnItemSelected() {

    }

    @Override
    public void refresh() {

    }
}

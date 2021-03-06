package com.apphouse.businessscheduler.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.apphouse.businessscheduler.main.decorator.OneDayDecorator;
import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.vo.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static android.support.v4.util.Preconditions.checkNotNull;
import static com.apphouse.businessscheduler.data.DataHelper.dataHelper;


public class MainPresenter implements MainContract.Presenter {

    private final Context context;
    private MainContract.View mainView;
    private String selectedDate;


    @SuppressLint("RestrictedApi")
    public MainPresenter(@NonNull MainContract.View mainView, Context context) {
        this.mainView = checkNotNull(mainView, "tasksView cannot be null!");
        mainView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void goToAddPageOnAddBtnClicked() {

    }

    @Override
    public void goToDetailPage() {

    }

    @Override
    public void actionOnDateClicked(CalendarDay date) {
        setSelectedDateData(date);
        if(isOverflowDate(date)) {
            setSchedulsForADay(date);
            mainView.showPreviewOnItemSelected();
        }
        else
            goToAddPageOnAddBtnClicked();
    }

    private void setSchedulsForADay(CalendarDay date) {
        ArrayList<Schedule> schedulesForADay = dataHelper.getSchedulesForADay(date.getDay(), selectedDate);
        dataHelper.setSchedulsForADay(schedulesForADay);
    }

    @Override
    public void setSelectedDateData(CalendarDay date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        selectedDate = formatter.format(date.getDate());
    }

    private boolean isOverflowDate(CalendarDay date) {
        ArrayList<Schedule> schedulesForADay = dataHelper.getSchedulesForADay(date.getDay(), selectedDate);
        if(schedulesForADay == null)
            return  false;
        else
            return schedulesForADay.size() > 2;
    }

    @Override
    public void reloadCurrentPageData(MaterialCalendarView calendarView, CalendarDay calendarDay) {
        calendarView.removeDecorators();
        calendarView.addDecorator(new OneDayDecorator(context, calendarDay));
        Log.d("처음 나오는 일자", String.valueOf(calendarView.getCurrentDate()));
    }

    @Override
    public void refresh() {

    }
}

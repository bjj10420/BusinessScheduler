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
        if(isOverflowDate(date))
            mainView.showPreviewOnItemSelected();
        else
            goToAddPageOnAddBtnClicked();
    }

    @Override
    public void setSelectedDateData(CalendarDay date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        selectedDate = formatter.format(date.getDate());
    }

    private boolean isOverflowDate(CalendarDay date) {
        ArrayList<Schedule> schedulesForADay = getSchedulesForADay(date);
        if(schedulesForADay == null)
            return  false;
        else
            return schedulesForADay.size() > 2;
    }

    private ArrayList<Schedule> getSchedulesForADay(CalendarDay date) {
        HashMap<Integer, ArrayList<Schedule>> schedulesForAMonth =
                dataHelper.getScheduleMapForAMonth(Integer.parseInt(Util.getYearMonthFromDate(selectedDate)));
        ArrayList<Schedule> schedulesForADay = schedulesForAMonth.get(date.getDay());
        return  schedulesForADay;
    }

    @Override
    public void reloadCurrentPageData(MaterialCalendarView calendarView, CalendarDay calendarDay) {
        calendarView.removeDecorators();
        calendarView.addDecorator(new OneDayDecorator(context, calendarDay));
    }

    @Override
    public void goToDetailPageOnItemSelected() {

    }

    @Override
    public void refresh() {

    }
}

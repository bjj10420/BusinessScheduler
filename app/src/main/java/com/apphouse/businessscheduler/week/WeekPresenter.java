package com.apphouse.businessscheduler.week;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.apphouse.businessscheduler.vo.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static android.support.v4.util.Preconditions.checkNotNull;
import static com.apphouse.businessscheduler.data.DataHelper.dataHelper;


public class WeekPresenter implements WeekContract.Presenter {

    private final Context context;
    private WeekContract.View mainView;
    private String selectedDate;
    private HashMap<Integer, ArrayList<Schedule>> schedulesForAMonthOpened;

    @SuppressLint("RestrictedApi")
    public WeekPresenter(@NonNull WeekContract.View mainView, Context context, CalendarDay calendarDay) {
        this.mainView = checkNotNull(mainView, "tasksView cannot be null!");
        this.context = context;
        mainView.setPresenter(this);
        loadData(calendarDay);
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
    public void actionOnCellClicked(CalendarDay date) {

    }

    @Override
    public void setSelectedDateData(CalendarDay date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        selectedDate = formatter.format(date.getDate());
    }

    @Override
    public void reloadCurrentPageData(MaterialCalendarView calendarView, CalendarDay calendarDay) {
        loadData(calendarDay);
        Log.d("처음 나오는 일자", String.valueOf(calendarDay));
    }

    private void loadData(CalendarDay currentPageCalendarDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String theYearMonth = format.format(currentPageCalendarDay.getDate());
        Log.d("loadData 오늘날짜 확인", format.format(currentPageCalendarDay.getDate()));
        schedulesForAMonthOpened =  dataHelper.getScheduleMapForAMonth(Integer.parseInt(theYearMonth));
    }

    @Override
    public void refresh() {

    }
}

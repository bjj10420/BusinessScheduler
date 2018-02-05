package com.apphouse.businessscheduler.main.decorator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.apphouse.businessscheduler.vo.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.apphouse.businessscheduler.data.DataHelper.dataHelper;

/**
 * Decorate a day by making the text big and bold
 */
public class OneDayDecorator implements DayViewDecorator {

    Context context;
    private HashMap<Integer, ArrayList<Schedule>> schedulesForAMonthOpened;
    private CalendarDay calendarDay;
    private DayViewFacade dayViewFacade;
    private ArrayList<Schedule> schedulesForADay;

    // 초기화시
    public OneDayDecorator(Context context) {
        calendarDay = CalendarDay.today();
        Log.d("초기화시 오늘날짜 확인", String.valueOf(calendarDay.getDate()));
        this.context = context;
        loadData();
    }

    // 페이징시
    public OneDayDecorator(Context context, CalendarDay calendarDay) {
        this.calendarDay = calendarDay;
        this.context = context;
        loadData();
    }

    private void loadData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String theYearMonth = format.format(calendarDay.getDate());
        Log.d("loadData 오늘날짜 확인", format.format(calendarDay.getDate()));
        schedulesForAMonthOpened =  dataHelper.getScheduleMapForAMonth(Integer.parseInt(theYearMonth));
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        if(schedulesForAMonthOpened != null &&
                schedulesForAMonthOpened.containsKey(day.getDay())) {
            schedulesForADay = schedulesForAMonthOpened.get(day.getDay());
            Log.d("호출된 shouldDecorate 테스트", "호출됨");
            decorate(dayViewFacade);
            return true;
        }
        else
            return false;
//          return  day.getDay() == 1 || day.getDay() == 5;
    }

    @SuppressLint("NewApi")
    @Override
    public void decorate(DayViewFacade view) {
//        view.addSpan(new StyleSpan(Typeface.BOLD));
//        view.addSpan(new RelativeSizeSpan(1.4f));
        Log.d("호출된 데코레이트 테스트", "호출됨 day = " + schedulesForADay);
          this.dayViewFacade = view;
          view.addSpan(new CustomSpan(Color.parseColor("#404040"), context, schedulesForADay));
    }

    /**
     * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
     */
    public void setCalendarDay(Date calendarDay) {
        this.calendarDay = CalendarDay.from(calendarDay);
    }
}

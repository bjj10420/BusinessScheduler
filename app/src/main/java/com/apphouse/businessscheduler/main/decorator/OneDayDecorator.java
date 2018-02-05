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
import java.util.Date;
import java.util.HashMap;

import static com.apphouse.businessscheduler.data.DataHelper.dataHelper;

/**
 * Decorate a day by making the text big and bold
 */
public class OneDayDecorator implements DayViewDecorator {

    Context context;
    private HashMap<Integer, Schedule> schedulesForAMonthOpened;
    private CalendarDay today;
    CalendarDay day;
    private DayViewFacade dayViewFacade;

    public OneDayDecorator(Context context) {
        today = CalendarDay.today();
        this.context = context;
        loadData();
    }

    private void loadData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String theMonth = format.format(today.getDate());
        Log.d("오늘날짜 확인", format.format(today.getDate()));
        schedulesForAMonthOpened =  dataHelper.getScheduleMapForAMonth(Integer.parseInt(theMonth));
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {

        if(schedulesForAMonthOpened.containsKey(day.getDay())) {
            this.day = day;
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
        Log.d("호출된 데코레이트 테스트", "호출됨 day = " + day);
          this.dayViewFacade = view;
          view.addSpan(new CustomSpan(Color.parseColor("#404040"), context, day));
    }

    /**
     * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
     */
    public void setToday(Date today) {
        this.today = CalendarDay.from(today);
    }
}

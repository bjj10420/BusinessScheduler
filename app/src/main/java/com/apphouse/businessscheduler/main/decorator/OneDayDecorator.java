package com.apphouse.businessscheduler.main.decorator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.apphouse.businessscheduler.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Date;

/**
 * Decorate a day by making the text big and bold
 */
public class OneDayDecorator implements DayViewDecorator {

    Context context;
    private CalendarDay date;
    CalendarDay day;
    private DayViewFacade dayViewFacade;

    public OneDayDecorator(Context context) {
        date = CalendarDay.today();
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {

        if(day.getDay() == 1 || day.getDay() == 5) {
            this.day = day;
            Log.d("호출된 shouldDecorate 테스트", "호출됨");
            decorate(dayViewFacade);
            return true;
        }
        else
            return false;
//          return  day.getDay() == 1 || day.getDay() == 5;
//        return true;
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
    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}

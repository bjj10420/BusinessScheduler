package com.apphouse.businessscheduler.week;


import android.widget.CalendarView;

import com.apphouse.businessscheduler.databinding.FragmentWeekBinding;
import com.apphouse.businessscheduler.inter.BaseView;
import com.apphouse.businessscheduler.inter.CoreActivityPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public interface WeekContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends CoreActivityPresenter {

    void actionOnCellClicked(CalendarDay date);

    void setSelectedDateData(CalendarDay date);

    void reloadCurrentPageData(MaterialCalendarView calendarView, CalendarDay date);

    }


}

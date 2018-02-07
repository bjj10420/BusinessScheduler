package com.apphouse.businessscheduler.week;


import com.apphouse.businessscheduler.inter.BaseView;
import com.apphouse.businessscheduler.inter.CoreActivityPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public interface WeekContract {

    interface View extends BaseView<Presenter> {

        void showPreviewOnItemSelected();

        void hidePreviewOnItemSelected();

    }

    interface Presenter extends CoreActivityPresenter {

    void goToAddPageOnAddBtnClicked();

    void goToDetailPage();

    void actionOnDateClicked(CalendarDay date);

    void setSelectedDateData(CalendarDay date);

    void reloadCurrentPageData(MaterialCalendarView calendarView, CalendarDay date);

    }


}

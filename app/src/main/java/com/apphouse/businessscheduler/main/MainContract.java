package com.apphouse.businessscheduler.main;


import com.apphouse.businessscheduler.inter.BaseView;
import com.apphouse.businessscheduler.inter.CoreActivityPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showPreviewOnItemSelected();

        void hidePreviewOnItemSelected();

    }

    interface Presenter extends CoreActivityPresenter {

    void goToAddPageOnAddBtnClicked();

    void setSelectedDateData(CalendarDay date);

    void reloadCurrentPageData(MaterialCalendarView calendarView, CalendarDay date);

    }


}

package com.apphouse.businessscheduler.main;


import com.apphouse.businessscheduler.inter.BasePresenter;
import com.apphouse.businessscheduler.inter.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        boolean showToast();
    }

    interface Presenter extends BasePresenter {
        void onClick();
   }
}

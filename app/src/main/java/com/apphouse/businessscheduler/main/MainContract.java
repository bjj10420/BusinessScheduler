package com.apphouse.businessscheduler.main;


import com.apphouse.businessscheduler.inter.BasePresenter;
import com.apphouse.businessscheduler.inter.BaseView;
import com.apphouse.businessscheduler.inter.CoreActivityPresenter;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showPreviewOnItemSelected();

        void hidePreviewOnItemSelected();

    }

    interface Presenter extends CoreActivityPresenter {

        void goToAddPageOnAddBtnClicked();

        void setSelectedDateData();

        // 페이징시
        void reloadCurrentPageData();

    }
}

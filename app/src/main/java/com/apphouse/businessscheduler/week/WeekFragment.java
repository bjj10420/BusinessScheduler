package com.apphouse.businessscheduler.week;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.databinding.FragmentMainBinding;
import com.apphouse.businessscheduler.databinding.FragmentWeekBinding;
import com.apphouse.businessscheduler.main.decorator.OneDayDecorator;
import com.apphouse.businessscheduler.main.preview.PreViewDialog;
import com.apphouse.businessscheduler.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class WeekFragment extends Fragment implements WeekContract.View {

    private WeekContract.Presenter presenter;
    private FragmentWeekBinding binding;
    private PreViewDialog preViewDialog;

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("WeekFragment onCreateView", "테스트");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week, container, false);
        View fragmentView = binding.getRoot();
        initView();
        initPrenter();
        return fragmentView;
    }

    private void initPrenter() {
        presenter = new WeekPresenter(this, getContext(), binding.calendarView.getCurrentDate());
    }

    private void initView() {
        initCalendarView(binding.calendarView);
    }

    private void initEvent() {
        initCalendarViewEvent();
    }

    private void initCalendarViewEvent() {


        binding.calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView calendarView, CalendarDay date) {
                presenter.reloadCurrentPageData(calendarView, date);
            }
        });
    }

    private void initCalendarView(MaterialCalendarView calendarView) {
        int calendarViewCellHeight = 30;
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
        calendarView.setTileHeightDp(calendarViewCellHeight);
        Log.d("처음 나오는 일자", String.valueOf(calendarView.getCurrentDate()));
    }

    @Override
    public void setPresenter(WeekContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
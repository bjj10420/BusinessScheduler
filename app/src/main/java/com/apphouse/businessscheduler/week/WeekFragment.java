package com.apphouse.businessscheduler.week;

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
import com.apphouse.businessscheduler.main.decorator.OneDayDecorator;
import com.apphouse.businessscheduler.main.preview.PreViewDialog;
import com.apphouse.businessscheduler.util.Util;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class WeekFragment extends Fragment implements WeekContract.View {

    private WeekContract.Presenter presenter;
    private FragmentMainBinding binding;
    private PreViewDialog preViewDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View fragmentView = binding.getRoot();
        initView();
        initEvent();
        return fragmentView;
    }

    private void initView() {
        initCalendarView(binding.calendarView);
        initPreView();
    }

    private void initPreView() {
        preViewDialog = new PreViewDialog(getContext());
    }

    private void initEvent() {
        initCalendarViewEvent();
    }

    private void initCalendarViewEvent() {
        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                presenter.actionOnDateClicked(date);
            }
        });

        binding.calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView calendarView, CalendarDay date) {
                presenter.reloadCurrentPageData(calendarView, date);
            }
        });
    }

    private void initCalendarView(MaterialCalendarView calendarView) {
        int calendarViewCellHeight = 70;
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
        calendarView.setTileHeightDp(calendarViewCellHeight);
        calendarView.addDecorator(new OneDayDecorator(getContext()));
        Log.d("처음 나오는 일자", String.valueOf(calendarView.getCurrentDate()));
    }

    @Override
    public void setPresenter(WeekContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showPreviewOnItemSelected() {
        preViewDialog.showPreViewDialog();
    }

    @Override
    public void hidePreviewOnItemSelected() {

    }

    private int getCellHeight() {
        return (int) (Util.getScreenSize(getContext()).y - Util.convertDpToPixel(50)) / 8;
    }

    public static WeekFragment newInstance() {
        return new WeekFragment();
    }

}
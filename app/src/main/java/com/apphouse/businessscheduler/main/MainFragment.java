package com.apphouse.businessscheduler.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.databinding.FragmentMainBinding;
import com.apphouse.businessscheduler.main.decorator.OneDayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter presenter;
    private FragmentMainBinding binding;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showPreviewOnItemSelected() {

    }

    @Override
    public void hidePreviewOnItemSelected() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View fragmentView = binding.getRoot();
        initView();
        initEvent();
        return fragmentView;
    }

    private void initView() {
        initCalendarView(binding.calendarView);
    }

    private void initEvent() {
        initCalendarViewEvent();
    }

    private void initCalendarViewEvent() {
        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                presenter.onDateSelected(date);
            }
        });
    }


    private void initCalendarView(MaterialCalendarView calendarView) {
        calendarView.setTileHeightDp(60);
        calendarView.addDecorator(new OneDayDecorator(getContext()));
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

}
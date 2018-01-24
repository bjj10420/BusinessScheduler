package com.apphouse.businessscheduler.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
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

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter presenter;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean showToast() {
        Toast.makeText(getContext(), "MVP Test", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View fragmentView = binding.getRoot();
        initView(binding);
        return fragmentView;
    }

    private void initView(FragmentMainBinding binding) {
        initCalendarView(binding.calendarView);
    }

    private void initCalendarView(MaterialCalendarView calendarView) {
        calendarView.setTileHeightDp(60);
        calendarView.addDecorator(new OneDayDecorator(getContext()));
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

}
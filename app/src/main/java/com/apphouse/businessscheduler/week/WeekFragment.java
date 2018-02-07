package com.apphouse.businessscheduler.week;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.databinding.FragmentWeekBinding;
import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.week.adapter.ScheduleGridAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class WeekFragment extends Fragment implements WeekContract.View {

    private WeekContract.Presenter presenter;
    private FragmentWeekBinding binding;
    private boolean gridViewResized;

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("WeekFragment onCreateView", "테스트");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week, container, false);
        View fragmentView = binding.getRoot();
        initView();
        initEvent();
        initPrenter();
        return fragmentView;
    }

    private void initPrenter() {
        presenter = new WeekPresenter(this, getContext(), binding.calendarView.getCurrentDate());
    }

    private void initView() {
        initCalendarView(binding.calendarView);
        initTimePanelView();
        initScheduleGridView();
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

    private void initTimePanelView() {
        for (int i = 0; i <= 24; i++) {
            binding.timePanel.addView(makeTimePanelBox(i));
        }
    }

    private void initScheduleGridView() {
        binding.scheduleGridView.setAdapter(new ScheduleGridAdapter(getContext()));
        binding.scheduleGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!gridViewResized) {
                    gridViewResized = true;
                    resizeGridView(binding.scheduleGridView, 175, 7);
                }
            }
        });
    }

    private View makeTimePanelBox(int time) {
        TextView timePanleBox = new TextView(getContext());
        LinearLayout.LayoutParams preViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) Util.convertDpToPixel(50));
        timePanleBox.setGravity(Gravity.CENTER);
        timePanleBox.setLayoutParams(preViewParams);
        timePanleBox.setText(time < 10 ? "0" + time : String.valueOf(time));
        return timePanleBox;
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

    private void resizeGridView(GridView gridView, int items, int columns) {
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        int oneRowHeight = gridView.getHeight();
        int rows = (int) (items / columns);
        params.height = oneRowHeight * rows;
        gridView.setLayoutParams(params);
    }

}
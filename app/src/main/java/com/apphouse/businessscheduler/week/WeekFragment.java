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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.databinding.FragmentWeekBinding;
import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.vo.Schedule;
import com.apphouse.businessscheduler.week.adapter.ScheduleGridAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static com.apphouse.businessscheduler.data.DataHelper.dataHelper;

public class WeekFragment extends Fragment implements WeekContract.View {

    private WeekContract.Presenter presenter;
    private FragmentWeekBinding binding;
    private ScheduleGridAdapter adapter;

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
        initScheduleGridView();
    }

    @SuppressLint("LongLogTag")
    private void initCalendarView(MaterialCalendarView calendarView) {
        int calendarViewCellHeight = 30;
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
        calendarView.setTileHeightDp(calendarViewCellHeight);
    }

    private void initScheduleGridView() {
        HashMap<Integer, ArrayList<Schedule>> columIndexesWithSchedule = checkAllSchedulesAndSaveIndexes(binding.calendarView);
        initScheduleGridViewAdapter(columIndexesWithSchedule);
    }

    private HashMap<Integer, ArrayList<Schedule>> checkAllSchedulesAndSaveIndexes(MaterialCalendarView calendarView) {
        Calendar newCalendar = calendarView.getCurrentDate().getCalendar();
        HashMap<Integer, ArrayList<Schedule>> columnIndexesWithSchedule = new HashMap<Integer, ArrayList<Schedule>>();
        findColumnIndexesWithSchedule(newCalendar, columnIndexesWithSchedule);
        return columnIndexesWithSchedule;
    }

    private void findColumnIndexesWithSchedule(Calendar newCalendar, HashMap<Integer, ArrayList<Schedule>> columnIndexesWithSchedule) {
        for (int columnIndex = 0; columnIndex <= 6; columnIndex++) {
            ArrayList<Schedule> scheduleListForADay = getScheduleDataForIndex(newCalendar, columnIndex);
            saveColumIndexWithSchedules(scheduleListForADay, columnIndex, columnIndexesWithSchedule);
        }
    }

    @SuppressLint("LongLogTag")
    private ArrayList<Schedule> getScheduleDataForIndex(Calendar newCalendar, int index) {
        newCalendar.add(Calendar.DAY_OF_WEEK, index);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String selectedDate = formatter.format(newCalendar.getTime());
        ArrayList<Schedule> schedulesForAday = dataHelper.getSchedulesForADay(newCalendar.getTime().getDate(), selectedDate);
        newCalendar.add(Calendar.DAY_OF_WEEK, -index);
        return schedulesForAday;
    }

    private void saveColumIndexWithSchedules(ArrayList<Schedule> scheduleListForADay, int index, HashMap<Integer, ArrayList<Schedule>> columIndexesWithSchedule) {
        if(scheduleListForADay != null)
            columIndexesWithSchedule.put(index, scheduleListForADay);
    }



    private void initScheduleGridViewAdapter(HashMap<Integer, ArrayList<Schedule>> columIndexesWithSchedule) {
        adapter = new ScheduleGridAdapter(getContext(), columIndexesWithSchedule);
        binding.scheduleGridView.setAdapter(adapter);
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

    @Override
    public void setPresenter(WeekContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
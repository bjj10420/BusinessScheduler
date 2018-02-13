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
import com.apphouse.businessscheduler.vo.Schedule;
import com.apphouse.businessscheduler.week.adapter.ScheduleGridAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    private void initScheduleGridView() {
        initScheduleGridViewAdapter();
        initScheduleGridData();
    }

    private void initScheduleGridData() {
        binding.scheduleGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                loadScheduleGridViewWithData(binding.calendarView);
            }
        });
    }

    private void initScheduleGridViewAdapter() {
        adapter = new ScheduleGridAdapter(getContext());
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

    @SuppressLint("LongLogTag")
    private void initCalendarView(MaterialCalendarView calendarView) {
        int calendarViewCellHeight = 30;
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
        calendarView.setTileHeightDp(calendarViewCellHeight);
    }

    private void loadScheduleGridViewWithData(MaterialCalendarView calendarView) {
        Calendar newCalendar = calendarView.getCurrentDate().getCalendar();
        for(int i = 0; i <= 6; i++){
            ArrayList<Schedule> scheduleListForADay = getScheduleDataForIndex(newCalendar, i);
            fillSchedulesForAday(scheduleListForADay, i+1);
        }
    }

    private void fillSchedulesForAday(ArrayList<Schedule> scheduleListForADay, int beginIndex) {

        if(scheduleListForADay == null) return;

        for(Schedule schedule : scheduleListForADay) {
            Log.d("스케쥴좀 확인하지요", String.valueOf(schedule.getDate()));
            Log.d("스케쥴좀 확인하지요", String.valueOf(schedule.getFromTime()));
            Log.d("스케쥴좀 확인하지요", String.valueOf(schedule.getToTime()));
            Log.d("스케쥴좀 확인하지요", String.valueOf(schedule.getScheduleName()));
            String fromTime = schedule.getFromTime();
            String toTime = schedule.getToTime();
            int [] theGridIndexes = findGridIndexes(beginIndex, fromTime, toTime);
            setGridViewsContents(theGridIndexes, schedule.getScheduleName());
        }
    }

    private int[] findGridIndexes(int beginIndex, String fromTime, String toTime) {
        int[] indexes = new int[Integer.parseInt(toTime) - Integer.parseInt(fromTime)];
        for (int j = 0; j < indexes.length; j++) {
            int theIndex = beginIndex + ((Integer.parseInt(fromTime)) * 8) + (j * 8);
            Log.d("인덱스값을 확인해보아요", String.valueOf(theIndex));
            indexes[j] = theIndex;
        }
        return indexes;
    }

    private void setGridViewsContents(int[] theGridIndexes, String scheduleName) {
        for(int gridIndex : theGridIndexes){
            Log.d("이게 제대로 맞긴합니까?",
                    String.valueOf((binding.scheduleGridView.getChildAt(gridIndex)
                    )));
            ((TextView)binding.scheduleGridView.getChildAt(gridIndex).findViewById(R.id.gridItemText)).setText(scheduleName);
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

    @Override
    public void setPresenter(WeekContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
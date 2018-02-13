package com.apphouse.businessscheduler.week.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.vo.Schedule;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleGridAdapter extends BaseAdapter {

    private  HashMap<Integer, ArrayList<Schedule>> columnIndexesWithSchedule;
    private  HashMap<Integer, ArrayList<String>> gridContentHashMap;

    LayoutInflater li;

    public ScheduleGridAdapter(Context context, HashMap<Integer, ArrayList<Schedule>> columIndexesWithSchedule) {
        initField(context, columIndexesWithSchedule);
        prepareGridContent();
    }

    private void initField(Context context, HashMap<Integer, ArrayList<Schedule>> columIndexesWithSchedule) {
        this.li = LayoutInflater.from(context);
        this.columnIndexesWithSchedule = columIndexesWithSchedule;
        this.gridContentHashMap = new HashMap<Integer, ArrayList<String>>();
    }

    private void prepareGridContent() {
        for(Integer columnIndex : columnIndexesWithSchedule.keySet()){
            fillSchedulesForAday(columnIndexesWithSchedule.get(columnIndex), columnIndex + 1);
        }
    }

    @Override
    public int getCount() {
        return 176;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View gridItemView, ViewGroup viewGroup) {
        Log.d("getView콜", "position = " + position + " view = " + gridItemView);
            gridItemView = makeGridItemView(position);
            gridItemView.setTag(position);

        return gridItemView;
    }

    private View makeGridItemView(int position) {
        View gridItemView = null;
        if (isTimePanel(position))
            gridItemView = makeGridTimePanelItemView(position);
        else
            gridItemView = makeNormalItemView(position);
        return gridItemView;
    }

    private boolean isTimePanel(int position) {
        return position % 8 == 0;
    }

    private View makeGridTimePanelItemView(int position) {
        View timePanelItemView = li.inflate(R.layout.schedule_grid_time_panel_item, null);
        setTimePanelView(timePanelItemView, position);
        return timePanelItemView;
    }
    private void setTimePanelView(View timePanelItemView, int position) {
        int hour = position / 8;
        if(hour == 24)
            hour = 23;
        ((TextView) timePanelItemView.findViewById(R.id.gridTimePanelItemText)).setText(
                hour < 10 ? "0" + hour : String.valueOf(hour));
    }

    private View makeNormalItemView(int position) {
        View normalItemView = li.inflate(R.layout.schedule_grid_item, null);
        setNormalItemView(normalItemView, position);
        return normalItemView;
    }

    private void setNormalItemView(View normalItemView, int position) {
        if(isDataColumn(position)) {

            ((TextView) normalItemView.findViewById(R.id.gridItemText)).setText(String.valueOf(position));
        }
    }

    private boolean isDataColumn(int position) {
        return  columnIndexesWithSchedule.containsKey(getColumnFromPosition(position));
    }

    // 칼럼의 인덱스는 맨왼쪽의 시간칼럼을 제외하고나서부터 0으로 시작
    private Integer getColumnFromPosition(int position) {
        return  (position - 1) % 8;
    }

    private void fillSchedulesForAday(ArrayList<Schedule> scheduleListForADay, int beginIndex) {
        for (Schedule schedule : scheduleListForADay) {
            Log.d("스케쥴좀 확인하지요", String.valueOf(schedule.getScheduleName()));
            String fromTime = schedule.getFromTime();
            String toTime = schedule.getToTime();
            int[] theGridIndexes = findGridIndexes(beginIndex, fromTime, toTime);
//            setGridViewsContents(theGridIndexes, schedule.getScheduleName());
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
        for (int gridIndex : theGridIndexes) {
//            ((TextView) binding.scheduleGridView.getChildAt(gridIndex).findViewById(R.id.gridItemText)).setText(scheduleName);
        }
    }
}

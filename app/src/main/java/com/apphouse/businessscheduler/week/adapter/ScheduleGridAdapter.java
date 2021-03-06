package com.apphouse.businessscheduler.week.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.vo.Schedule;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleGridAdapter extends BaseAdapter {

    private  HashMap<Integer, ArrayList<Schedule>> columnIndexesWithSchedule;
    private  HashMap<Integer, ArrayList<String>> gridContentHashMap;

    LayoutInflater li;
    private Context context;

    public ScheduleGridAdapter(Context context, HashMap<Integer, ArrayList<Schedule>> columIndexesWithSchedule) {
        initField(context, columIndexesWithSchedule);
        prepareGridContent();
    }

    private void initField(Context context, HashMap<Integer, ArrayList<Schedule>> columIndexesWithSchedule) {
        this.context = context;
        this.li = LayoutInflater.from(context);
        this.columnIndexesWithSchedule = columIndexesWithSchedule;
        this.gridContentHashMap = new HashMap<Integer, ArrayList<String>>();
    }

    private void prepareGridContent() {
        for(Integer columnIndex : columnIndexesWithSchedule.keySet()){
            fillGridContentHashMapForADay(columnIndexesWithSchedule.get(columnIndex), columnIndex + 1);
        }
    }

    private void fillGridContentHashMapForADay(ArrayList<Schedule> scheduleListForADay, int beginIndex) {
        for (Schedule schedule : scheduleListForADay) {
            String fromTime = schedule.getFromTime();
            String toTime = schedule.getToTime();
            int[] theGridIndexes = findGridIndexes(beginIndex, fromTime, toTime);
            saveGridContentsInIndexes(theGridIndexes, schedule.getScheduleName());
        }
    }

    private void saveGridContentsInIndexes(int[] theGridIndexes, String scheduleName) {
            for(int i = 0; i < theGridIndexes.length; i++) {
                if(gridContentHashMap.get(theGridIndexes[i]) == null) gridContentHashMap.put(theGridIndexes[i], new ArrayList<String>());
                gridContentHashMap.get(theGridIndexes[i]).add(scheduleName);
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
        if(isDataColumn(position) && isGridWithSchedule(position))
            setGridContent(normalItemView, position);
        else
            ((TextView) normalItemView.
                    findViewById(R.id.gridItemText)).setText(String.valueOf(position));
    }

    private void setGridContent(View normalItemView, int position) {
        if(isGridContentSingle(position))
            setSingleGridContent(normalItemView, position);
        else
            setMuiltiGridContents(normalItemView, position);
    }

    private void setSingleGridContent(View normalItemView, int position) {
        ((TextView) normalItemView.
                findViewById(R.id.gridItemText)).setText(String.valueOf(gridContentHashMap.get(position).get(0)));
    }

    private void setMuiltiGridContents(View normalItemView, int position) {
        normalItemView.findViewById(R.id.gridItemText).setVisibility(View.GONE);
        for(String gridContent : gridContentHashMap.get(position)) {
            TextView tv = new TextView(context);
            setTv(tv, gridContent);
              ((LinearLayout)normalItemView).addView(tv);
        }
    }

    private void setTv(TextView tv, String gridContent) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        tv.setLayoutParams(params);
        tv.setTextSize(Util.convertDpToPixel(2));
        tv.setText(gridContent);
    }

    private boolean isGridContentSingle(int position) {
        return  gridContentHashMap.get(position).size() == 1;
    }

    private boolean isDataColumn(int position) {
        return  columnIndexesWithSchedule.containsKey(getColumnFromPosition(position));
    }

    private boolean isGridWithSchedule(int position) {
        return gridContentHashMap.containsKey(position);
    }

    // 칼럼의 인덱스는 맨왼쪽의 시간칼럼을 제외하고나서부터 0으로 시작
    private Integer getColumnFromPosition(int position) {
        return  (position - 1) % 8;
    }

}

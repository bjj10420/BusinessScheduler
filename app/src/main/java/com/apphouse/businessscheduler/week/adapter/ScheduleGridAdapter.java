package com.apphouse.businessscheduler.week.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;

import java.util.HashSet;

public class ScheduleGridAdapter extends BaseAdapter {

    LayoutInflater li;
    boolean isSetOnce = false;
    private SparseArray<View> views;


    public ScheduleGridAdapter(Context context, HashSet<Integer> columIndexesWithSchedule) {
        this.li = LayoutInflater.from(context);
        views = new SparseArray<>();
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
        if(gridItemView == null) {
            gridItemView = makeGridItemView(position);
            gridItemView.setTag(position);
            views.put(position, gridItemView);
        }
        else
            gridItemView = views.get(position);

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
        if(((TextView) normalItemView.findViewById(R.id.gridItemText)).getText().equals(""))
        ((TextView) normalItemView.findViewById(R.id.gridItemText)).setText(String.valueOf(position));
    }

    private boolean isTimePanel(int position) {
        return position % 8 == 0;
    }

    public void setSetOnce(boolean setOnce) {
        isSetOnce = setOnce;
    }
}

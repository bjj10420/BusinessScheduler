package com.apphouse.businessscheduler.week.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;

public class ScheduleGridAdapter extends BaseAdapter {

    LayoutInflater li;

    public ScheduleGridAdapter(Context context) {
        this.li = LayoutInflater.from(context);
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

    private View makeGridTimePanelItemView(int position) {
        View timePanelItemView = li.inflate(R.layout.schedule_grid_time_panel_item, null);
        setTimePanelView(timePanelItemView, position);
        return timePanelItemView;
    }
    private void setTimePanelView(View timePanelItemView, int position) {
        int hour = position / 7;
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
        ((TextView) normalItemView.findViewById(R.id.gridItemText)).setText(String.valueOf(position));
    }

    private boolean isTimePanel(int position) {
        return position % 8 == 0;
    }


}

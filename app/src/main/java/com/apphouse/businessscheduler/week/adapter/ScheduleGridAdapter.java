package com.apphouse.businessscheduler.week.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
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
        return 198;
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

        gridItemView = li.inflate(R.layout.schedule_grid_item, null);
        gridItemView.setTag(position);
        setConvertViewContent(gridItemView, position);
        return gridItemView;
    }

    private void setConvertViewContent(View convertView, int position) {
        if (isTimePanel(convertView))
            setTimePanelView(convertView);

        setContentText(convertView, position);
    }

    private void setContentText(View convertView, int position) {
        ((TextView) convertView.findViewById(R.id.gridItemText)).setText(String.valueOf(position));
    }

    private boolean isTimePanel(View convertView) {
        return (Integer) convertView.getTag() % 9 == 0;
    }

    private void setTimePanelView(View convertView) {
        convertView.setBackgroundColor(Color.parseColor("#ffffff"));
    }

}

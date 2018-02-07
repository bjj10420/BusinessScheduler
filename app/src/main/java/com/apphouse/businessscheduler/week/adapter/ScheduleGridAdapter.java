package com.apphouse.businessscheduler.week.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;

public class ScheduleGridAdapter extends BaseAdapter{

    LayoutInflater li;

    public ScheduleGridAdapter(Context context) {
        this.li = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 175;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null)
            convertView = li.inflate(R.layout.schedule_grid_item, null);
        ((TextView)convertView.findViewById(R.id.gridItemText)).setText(String.valueOf(position));
        return convertView;
    }
}

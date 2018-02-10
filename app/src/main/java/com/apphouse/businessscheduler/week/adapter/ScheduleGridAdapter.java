package com.apphouse.businessscheduler.week.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.util.Util;

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

        gridItemView = makeGridItemView(position);
        gridItemView.setTag(position);
        setConvertViewContent(gridItemView, position);
        return gridItemView;
    }

    private View makeGridItemView(int position) {
        if (isTimePanel(position))
            return li.inflate(R.layout.schedule_grid_time_panel_item, null);
        else
            return li.inflate(R.layout.schedule_grid_item, null);
    }

    private void setConvertViewContent(View convertView, int position) {
//        if (isTimePanel(position))
//            setTimePanelView(convertView);

        setContentText(convertView, position);
    }

    private void setContentText(View convertView, int position) {
        if(isTimePanel(position))
        ((TextView) convertView.findViewById(R.id.gridTimePanelItemText)).setText(String.valueOf(position));
        else
        ((TextView) convertView.findViewById(R.id.gridItemText)).setText(String.valueOf(position));

    }

    private boolean isTimePanel(int position) {
        return position % 9 == 0;
    }

    private void setTimePanelView(View convertView) {
        convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        LinearLayout.LayoutParams preViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) Util.convertDpToPixel(150));
        convertView.setLayoutParams(preViewParams);
    }

}

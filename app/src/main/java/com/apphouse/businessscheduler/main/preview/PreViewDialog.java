package com.apphouse.businessscheduler.main.preview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.vo.Schedule;

import me.drakeet.materialdialog.MaterialDialog;

import static com.apphouse.businessscheduler.data.DataHelper.dataHelper;

public class PreViewDialog {

    Context context;

    public PreViewDialog(Context context) {
        this.context = context;
    }

    public void showPreViewDialog() {
        View contentView = makePreViewDialogContentView();
        makePreViewDialogAndShow(contentView);
    }

    private View makePreViewDialogContentView() {
        LinearLayout preView = new LinearLayout(context);
        setPreViewBasicOptions(preView);
        setPreViewContent(preView);
        return preView;
    }

    private void setPreViewBasicOptions(LinearLayout preView) {
        LinearLayout.LayoutParams preViewParams = makeLayoutParamsForPreView();
        preView.setOrientation(LinearLayout.VERTICAL);
        preView.setGravity(Gravity.CENTER);
        preView.setLayoutParams(preViewParams);
    }

    private LinearLayout.LayoutParams makeLayoutParamsForPreView() {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void setPreViewContent(LinearLayout preView) {
        for(Schedule schedule : dataHelper.getSchedulesForADay()){
            Log.d("뷰를 add했습니다", "스케쥴 정보는 " + schedule.getScheduleName());
            preView.addView(makeContentRowView(schedule));
        }
    }

    private View makeContentRowView(Schedule schedule) {
        View contentRowView = makeRowView();
        setRowContent(contentRowView, schedule);
        return  contentRowView;
    }

    private View makeRowView() {
        View contentRowView = LayoutInflater.from(context).inflate(R.layout.preview_row, null);
        contentRowView.setLayoutParams(makeLayoutParamsForRowView());
        return contentRowView;
    }

    private void setRowContent(View contentRowView, Schedule schedule) {
//        ((TextView)contentRowView.findViewById(R.id.rowContent)).setText(schedule.getScheduleName());
        ((TextView)contentRowView.findViewById(R.id.rowTime)).setText(schedule.getTime());
        ((TextView)contentRowView.findViewById(R.id.rowContent)).setText(schedule.getScheduleName());
    }

    private LinearLayout.LayoutParams makeLayoutParamsForRowView() {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) Util.convertDpToPixel(30));
    }

    private void makePreViewDialogAndShow(View contentView) {
        MaterialDialog dialog = new MaterialDialog(context);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle(dataHelper.getSchedulesForADay().size() + " Schedules");
        dialog.show();
    }
}

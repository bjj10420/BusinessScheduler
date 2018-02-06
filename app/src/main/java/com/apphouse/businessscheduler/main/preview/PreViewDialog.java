package com.apphouse.businessscheduler.main.preview;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apphouse.businessscheduler.R;
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
            Log.d("뷰를 add했습니다", "뷰를 add했습니다");
            preView.addView(makeContentRowView(schedule));
        }
    }

    private View makeContentRowView(Schedule schedule) {
        View contentRowView = LayoutInflater.from(context).inflate(R.layout.preview_row, null);
        contentRowView.setLayoutParams(makeLayoutParamsForRowView());
        ((TextView)contentRowView.findViewById(R.id.rowContent)).setText(schedule.getScheduleName());
        return  contentRowView;
    }

    private LinearLayout.LayoutParams makeLayoutParamsForRowView() {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void makePreViewDialogAndShow(View contentView) {
        MaterialDialog dialog = new MaterialDialog(context);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}

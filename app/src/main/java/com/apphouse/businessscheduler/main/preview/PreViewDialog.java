package com.apphouse.businessscheduler.main.preview;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
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
        // 각 버튼 뷰 레이아웃 파라메터
        LinearLayout.LayoutParams preViewParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        preView.setOrientation(LinearLayout.VERTICAL);
        preView.setGravity(Gravity.CENTER);
        preView.setLayoutParams(preViewParams);
        preView.addView(makeContentTextView());
        return preView;
    }

    private TextView makeContentTextView() {
        TextView contentTextView = new TextView(context);
        contentTextView.setText(dataHelper.getSchedulsForADay().get(0).getScheduleName());
        return  contentTextView;
    }

    private void makePreViewDialogAndShow(View contentView) {
        MaterialDialog dialog = new MaterialDialog(context);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}

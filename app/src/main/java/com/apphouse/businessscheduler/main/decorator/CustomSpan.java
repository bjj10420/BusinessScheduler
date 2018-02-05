package com.apphouse.businessscheduler.main.decorator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.vo.Schedule;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.prolificinteractive.materialcalendarview.CalendarDay;


public class CustomSpan implements LineBackgroundSpan {

    private final CalendarDay day;
    private final Schedule schedule;
    Context context;
    private final int color;

    public CustomSpan(int color, Context context, CalendarDay day, Schedule schedule) {
        this.color = color;
        this.context = context;
        this.day = day;
        this.schedule = schedule;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        if(day != null)
        makeRowLayout(canvas, paint);
        paint.setColor(oldColor);
    }

    private void makeRowLayout(Canvas canvas, Paint paint) {
        LinearLayout rowLayout = initRowLayout();
        fillRowLayout(rowLayout);
        drawRowLayoutOnTheCanvas(rowLayout, canvas, paint);
    }

    private LinearLayout initRowLayout() {
        LinearLayout rowLayout = new LinearLayout(context);
        LayoutParams rowLayoutParams = initRowLayoutParam();
        setRowLayoutBasicOptions(rowLayout, rowLayoutParams);
        return rowLayout;
    }

    private LayoutParams initRowLayoutParam() {
        LayoutParams rowLayoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        return  rowLayoutParams;
    }

    private void setRowLayoutBasicOptions(LinearLayout rowLayout,
                                          LayoutParams rowLayoutParams) {
        rowLayout.setOrientation(LinearLayout.VERTICAL);
        rowLayout.setLayoutParams(rowLayoutParams);
        rowLayout.setPadding(0, (int) Util.convertDpToPixel(18.0f), 0, 0);
    }

    private void fillRowLayout(LinearLayout rowLayout) {
        makeTextViewAndSpendToRowLayout(day.toString(), rowLayout);
        makeTextViewAndSpendToRowLayout(schedule.getScheduleName(), rowLayout);
//        makeTextViewAndSpendToRowLayout("테스트3", rowLayout);
//        makeIconViewAndSpendToRowLayout(rowLayout);
    }

    private void drawRowLayoutOnTheCanvas(LinearLayout rowLayout, Canvas canvas, Paint paint) {
        rowLayout.setDrawingCacheEnabled(true);
        measureRowLayout(rowLayout,canvas);
        drawRowLayout(rowLayout,canvas, paint);
    }

    private void drawRowLayout(LinearLayout rowLayout, Canvas canvas, Paint paint) {
        rowLayout.layout(0, 0, rowLayout.getMeasuredWidth(), rowLayout.getMeasuredHeight());
        canvas.drawBitmap(rowLayout.getDrawingCache(), 0, 0, paint);
        rowLayout.setDrawingCacheEnabled(false);
    }

    private void measureRowLayout(LinearLayout rowLayout, Canvas canvas) {
        rowLayout.measure(View.MeasureSpec.makeMeasureSpec(
                canvas.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(canvas.getHeight(),
                        View.MeasureSpec.EXACTLY));
    }

    private void makeTextViewAndSpendToRowLayout(String content, LinearLayout rowLayout) {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(7);
        tv.setText(content);
        tv.setTextColor(Color.BLACK);
        rowLayout.addView(tv);
    }

    @SuppressLint("NewApi")
    private void makeIconViewAndSpendToRowLayout(LinearLayout rowLayout) {
        View iconView = new View(context);
        iconView.setBackground(new IconicsDrawable(context, Ionicons.Icon.ion_ios_more));
        iconView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) Util.convertDpToPixel(3.0f)));
        rowLayout.addView(iconView);
    }


}



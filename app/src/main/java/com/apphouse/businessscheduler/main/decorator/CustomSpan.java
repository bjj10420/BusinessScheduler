package com.apphouse.businessscheduler.main.decorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apphouse.businessscheduler.util.Util;


public class CustomSpan implements LineBackgroundSpan {

    Context context;
    private final int color;

    public CustomSpan(int color, Context context) {
        this.color = color;
        this.context = context;
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
        LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        rowLayout.setOrientation(LinearLayout.VERTICAL);
        rowLayout.setLayoutParams(rowLayoutParams);
        rowLayout.setPadding(0, (int) Util.convertDpToPixel(15.0f), 0, 0);
        return rowLayout;
    }

    private void drawRowLayoutOnTheCanvas(LinearLayout rowLayout, Canvas canvas, Paint paint) {
        rowLayout.setDrawingCacheEnabled(true);
        rowLayout.measure(View.MeasureSpec.makeMeasureSpec(
                canvas.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(canvas.getHeight(),
                        View.MeasureSpec.EXACTLY));
        rowLayout.layout(0, 0, rowLayout.getMeasuredWidth(), rowLayout.getMeasuredHeight());
        canvas.drawBitmap(rowLayout.getDrawingCache(), 0, 0, paint);
        rowLayout.setDrawingCacheEnabled(false);
    }

    private void fillRowLayout(LinearLayout rowLayout) {
        makeTextViewAndSpendToRowLayout("옷홍옷", rowLayout);
        makeTextViewAndSpendToRowLayout("테스트2", rowLayout);
    }

    private void makeTextViewAndSpendToRowLayout(String content, LinearLayout rowLayout) {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(8);
        tv.setText(content);
        tv.setTextColor(Color.BLACK);
        rowLayout.addView(tv);
    }
}



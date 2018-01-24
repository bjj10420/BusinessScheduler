package com.apphouse.businessscheduler.main.decorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;
import android.view.View;
import android.widget.TextView;


public class CustomSpan implements LineBackgroundSpan {

    Context context;
    public static final float DEFAULT_RADIUS = 3;
    private final float radius;
    private final int color;

    public CustomSpan() {
        this.radius = DEFAULT_RADIUS;
        this.color = 0;
    }

    public CustomSpan(int color) {
        this.radius = DEFAULT_RADIUS;
        this.color = color;
    }

    public CustomSpan(float radius) {
        this.radius = radius;
        this.color = 0;
    }


    public CustomSpan(float radius, int color, Context context) {
        this.radius = radius;
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
//        canvas.drawCircle((left + right) / 2, bottom + radius, radius, paint);
//        canvas.drawText("테스트", (left + right) / 2, bottom, paint);

        TextView tv = new TextView(context);
        tv.setTextSize(8);
        tv.setText(" 테스트 \n 하이영");
        tv.setTextColor(Color.BLACK);
        tv.setDrawingCacheEnabled(true);
        tv.measure(View.MeasureSpec.makeMeasureSpec(
                canvas.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(canvas.getHeight(),
                        View.MeasureSpec.EXACTLY));
        tv.layout(0, 0, tv.getMeasuredWidth(), tv.getMeasuredHeight());

        // draw the bitmap from the drawingcache to the canvas
        canvas.drawBitmap(tv.getDrawingCache(), 0, 0, paint);

        // disable drawing cache
        tv.setDrawingCacheEnabled(false);


        paint.setColor(oldColor);
    }
}



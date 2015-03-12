package com.example.android.customviewp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;

/**
 * Created by Pashgan on 05.03.2015.
 */
public class LineChartView extends View {

    private static final int MIN_LINES = 4;
    private static final int MAX_LINES = 7;
    private static final int[] DISTANCES = { 1, 2, 5 };

    private float[] datapoints = new float[] {};
    private Paint paint = new Paint();

    public void setChartData(float[] datapoints) {
        this.datapoints = datapoints.clone();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawLineChart(canvas);
    }

    private void drawBackground(Canvas canvas) {
        float maxValue = getMax(datapoints);
        int range = getLineDistance(maxValue);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        for (int y = 0; y < maxValue; y += range) {
            final float yPos = getYPos(y);
            canvas.drawLine(0, yPos, getWidth(), yPos, paint);
        }
    }

    private int getLineDistance(float maxValue) {
        int distance;
        int distanceIndex = 0;
        int distanceMultiplier = 1;
        int numberOfLines = MIN_LINES;

        do {
            distance = DISTANCES[distanceIndex] * distanceMultiplier;
            numberOfLines = (int) FloatMath.ceil(maxValue / distance);

            distanceIndex++;
            if (distanceIndex == DISTANCES.length) {
                distanceIndex = 0;
                distanceMultiplier *= 10;
            }
        } while (numberOfLines < MIN_LINES || numberOfLines > MAX_LINES);

        return distance;
    }

    private void drawLineChart(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getXPos(0), getYPos(datapoints[0]));
        for (int i = 1; i < datapoints.length; i++) {
            path.lineTo(getXPos(i), getYPos(datapoints[i]));
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(0xFF33B5E5);
        paint.setAntiAlias(true);
        paint.setShadowLayer(4, 2, 2, 0x80000000);
        canvas.drawPath(path, paint);
        paint.setShadowLayer(0, 0, 0, 0);
    }



    private float getYPos(float value) {
        float height = getHeight() - getPaddingTop() - getPaddingBottom();
        float maxValue = getMax(datapoints);

        // масштабирования под высоту view
        value = (value / maxValue) * height;

        // инверсия
        value = height - value;

        // смещение чтобы учесть padding
        value += getPaddingTop();
        return value;
    }

    private float getXPos(float value) {
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float maxValue = datapoints.length - 1;

        // масштабирования под размер view
        value = (value / maxValue) * width;

        // смещение чтобы учесть padding
        value += getPaddingLeft();

        return value;
    }

    private float getMax(float[] array) {
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

}

package com.liun.chartview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Description :
 * Author : Liun
 * Date   : 2017/9/25 10:09
 * Email  : liun_coolman@foxmail.com
 */
public class ChartView extends BaseView {

    private float axisDivideSizeX;// X轴刻度数
    private float axisDivideSizeY;// Y轴刻度数
    private String[] axisScaleMarkValueX;// X轴刻度值
    private String[] axisScaleMarkValueY; // Y轴刻度值
    private int[][] columnInfo; // 需要绘制的矩形信息

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawXAxisScale(Canvas canvas, Paint mPaint) {
        // x轴刻度
        float cellWidth = width / axisDivideSizeX;

        for (int i = 0; i < axisDivideSizeX - 1; i++) {
            canvas.drawLine(coordinateX + cellWidth * (i + 1), coordinateY,
                    coordinateX + cellWidth * (i + 1), coordinateY - 10
                    , mPaint);
        }
    }

    @Override
    protected void drawYAxisScale(Canvas canvas, Paint mPaint) {
        // y轴刻度
        float cellWidth = heigit / axisDivideSizeY;

        for (int i = 0; i < axisDivideSizeY - 1; i++) {
            canvas.drawLine(coordinateX, coordinateY - (cellWidth * (i + 1)),
                    coordinateX + 10, coordinateY - (cellWidth * (i + 1))
                    , mPaint);

        }
    }

    @Override
    protected void drawXAxisScaleVaule(Canvas canvas, Paint mPaint) {
        // X轴每一个刻度上的值
        float cellWidth = width / axisDivideSizeX;
        for (int i = 0; i < axisDivideSizeX - 1; i++) {
            String str = axisScaleMarkValueX[i];
            float measureText = mPaint.measureText(str);
            float startX = (coordinateX + cellWidth * (i + 1)) - measureText / 2 - cellWidth / 2;
            canvas.drawText(str, startX, coordinateY + 50, mPaint);
        }
    }

    @Override
    protected void drawYAxisScaleVaule(Canvas canvas, Paint mPaint) {
        // Y轴每一个刻度上的值

        float cellWidth = heigit / axisDivideSizeY;

        for (int i = 0; i < axisDivideSizeY - 1; i++) {

            String str = axisScaleMarkValueY[i];
            float measureText = mPaint.ascent() + mPaint.descent();// 文字高度
            float startY = coordinateY - (cellWidth * (i + 1)) - measureText / 2;
            canvas.drawText(str, coordinateX - 100, startY, mPaint);

        }
    }

    @Override
    protected void drawColumn(Canvas canvas, Paint mPaint) {
        mPaint.setStyle(Paint.Style.FILL);
        // X轴每一个刻度宽
        float cellWidth = width / axisDivideSizeX;

        // 绘制矩形
        for (int i = 0; i < columnInfo.length; i++) {
            float info = Float.parseFloat(columnInfo[i][0] + "");
            int lenght = axisScaleMarkValueY.length;
            int lastValue = Integer.parseInt(axisScaleMarkValueY[lenght - 1]);
            float startY = coordinateY - heigit * (info / lastValue);

            mPaint.setColor(columnInfo[i][1]);
            canvas.drawRect(coordinateX + cellWidth * (i),
                    startY,
                    coordinateX + cellWidth * (i + 1),
                    coordinateY,
                    mPaint);
        }
    }

    /**
     * 设置x轴刻度数
     *
     * @param axisDivideSizeX
     */
    public void setAxisDivideSizeX(float axisDivideSizeX) {
        this.axisDivideSizeX = axisDivideSizeX;
    }

    /**
     * 设置y轴刻度数
     *
     * @param axisDivideSizeY
     */
    public void setAxisDivideSizeY(float axisDivideSizeY) {
        this.axisDivideSizeY = axisDivideSizeY;
    }

    /**
     * 每一个 X轴刻度值
     *
     * @param axisScaleMarkValueX
     */
    public void setAxisScaleMarkValueX(String[] axisScaleMarkValueX) {
        this.axisScaleMarkValueX = axisScaleMarkValueX;
    }

    /**
     * 每一个 Y轴刻度值
     *
     * @param axisScaleMarkValueY
     */
    public void setAxisScaleMarkValueY(String[] axisScaleMarkValueY) {
        this.axisScaleMarkValueY = axisScaleMarkValueY;
    }

    /**
     * 需要绘制的矩形信息
     *
     * @param columnInfo
     */
    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }
}

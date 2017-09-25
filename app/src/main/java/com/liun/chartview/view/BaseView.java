package com.liun.chartview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liun.chartview.R;

/**
 * Description :
 * Author : Liun
 * Date   : 2017/9/22 17:21
 * Email  : liun_coolman@foxmail.com
 */
public abstract class BaseView extends View {

    private Context mContext;
    private String mXAxisTitle;// x轴title
    private String mYAxisTitle;// y轴title
    private Paint mPaint;

    // 画布宽高
    public float width;
    public float heigit;

    // 画布坐标系起始坐标
    public float coordinateX = 200;
    public float coordinateY = 1800;

    private int mAxisTextColor;
    private float mAxisTextSize;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.custom_chart_view);
        mXAxisTitle = typedArray.getString(R.styleable.custom_chart_view_xAxisTitle);
        mYAxisTitle = typedArray.getString(R.styleable.custom_chart_view_yAxisTitle);
        mAxisTextColor = typedArray.getInt(R.styleable.custom_chart_view_mAxisTextColor, getResources().getColor(android.R.color.black));
        mAxisTextSize = typedArray.getFloat(R.styleable.custom_chart_view_mAxisTextSize, 16);

        // 回收typedArray
        if (typedArray != null) {
            typedArray.recycle();
        }
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth() - coordinateX - 100;
        heigit = getHeight() - 800;

        drawXAxis(canvas, mPaint);
        drawYAxis(canvas, mPaint);
        drawXAxisArrow(canvas, mPaint);
        drawYAisArrow(canvas, mPaint);

        // 需要具体实现的让子类使用抽象方法实现
        drawXAxisScale(canvas, mPaint);
        drawYAxisScale(canvas, mPaint);
        drawXAxisScaleVaule(canvas, mPaint);
        drawYAxisScaleVaule(canvas, mPaint);
        drawColumn(canvas, mPaint);
    }

    /**
     * X轴
     *
     * @param canvas
     * @param mPaint
     */
    private void drawXAxis(Canvas canvas, Paint mPaint) {
        mPaint.setFakeBoldText(true);
        canvas.drawLine(coordinateX, coordinateY, coordinateX + width, coordinateY, mPaint);
    }

    /**
     * Y轴
     *
     * @param canvas
     * @param mPaint
     */
    private void drawYAxis(Canvas canvas, Paint mPaint) {
        mPaint.setFakeBoldText(true);
        canvas.drawLine(coordinateX, coordinateY, coordinateX, coordinateY - heigit, mPaint);
    }

    /**
     * x轴箭头
     *
     * @param canvas
     * @param mPaint
     */
    private void drawXAxisArrow(Canvas canvas, Paint mPaint) {
        mPaint.setFakeBoldText(true);
        Path path = new Path();
        path.moveTo(coordinateX + width, coordinateY);
        path.lineTo(coordinateX + width - 20, coordinateY - 20);
        path.lineTo(coordinateX + width - 20, coordinateY + 20);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.drawText(mXAxisTitle, coordinateX + width - 50, coordinateY + 50, mPaint);
    }

    /**
     * Y轴箭头
     *
     * @param canvas
     * @param mPaint
     */
    private void drawYAisArrow(Canvas canvas, Paint mPaint) {
        mPaint.setFakeBoldText(true);
        Path path = new Path();
        path.moveTo(coordinateX, coordinateY - heigit);
        path.lineTo(coordinateX - 20, coordinateY - heigit + 20);
        path.lineTo(coordinateX + 20, coordinateY - heigit + 20);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.drawText(mYAxisTitle, coordinateX + 50, coordinateY - heigit + 50, mPaint);
    }

    protected abstract void drawXAxisScale(Canvas canvas, Paint mPaint);

    protected abstract void drawYAxisScale(Canvas canvas, Paint mPaint);

    protected abstract void drawXAxisScaleVaule(Canvas canvas, Paint mPaint);

    protected abstract void drawYAxisScaleVaule(Canvas canvas, Paint mPaint);

    protected abstract void drawColumn(Canvas canvas, Paint mPaint);


    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(mAxisTextColor);
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setAntiAlias(true);// 抗锯齿
            mPaint.setDither(true);// 抗抖动
        }
    }
}

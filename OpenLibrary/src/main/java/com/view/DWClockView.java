package com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.utils.DisplayUtil;
import java.util.Calendar;

/**
 * Created by nicestars on 2017/12/27.
 */

public class DWClockView extends ImageView {

    private Paint mPaint;//买只笔
    private int width = DisplayUtil.Dp2Px(getContext(), 280);
    private int height = DisplayUtil.Dp2Px(getContext(), 280);
    private int padding = 10;
    private int mHour;//小时
    private int mMinuate;//分钟
    private int mSecond;//秒
    private float mDegrees;//因为圆是360度 我们有12个刻度 所以就是360/12
    private int mHourLineLen;//时指针 线
    private int mMinuateLine;//分钟 线
    private int mSecondLine;//表钟线
    private Calendar mCalendar;
    private Handler mhanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };


    public DWClockView(Context context) {
        super(context);
    }

    public DWClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);

        mHourLineLen = (int) (width / 2 * 0.4);
        mMinuateLine = (int) (width / 2 * 0.7);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        canvasCenterCircle(canvas);
        canvasCircle(canvas);
        drawScale(canvas);
        drawStr(canvas);
        drawPointer(canvas);
        mhanlder.sendEmptyMessage(1);

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#666666"));
        //设置抗锯齿
        mPaint.setAntiAlias(true);
    }


    /**
     * @param canvas 画个点
     */
    private void canvasCenterCircle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(width / 2, height / 2, padding, mPaint);
    }

    /**
     * @param canvas 画个表盘
     */
    private void canvasCircle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width / 2, height / 2, width / 2 - 10, mPaint);
    }


    /**
     * 绘制刻度
     *
     * @param canvas
     */
    private void drawScale(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {//  整点长度加长
                mPaint.setStrokeWidth(10);
                canvas.drawLine(width / 2, padding, width / 2 , padding + 4 + 90, mPaint);
            } else {
                mPaint.setStrokeWidth(2);
                canvas.drawLine(width / 2, padding, width / 2 , padding + 4 + 40, mPaint);
            }
            canvas.rotate(6, width / 2, width / 2);
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawStr(Canvas canvas) {
        mPaint.setTextSize(60);
        mPaint.setColor(Color.BLACK);
        String str = "DW";
        int strW = (int) mPaint.measureText(str);
        canvas.drawText(str, width / 2 - strW / 2, width / 2 - DisplayUtil.Dp2Px(getContext(), 70), mPaint);

        mPaint.setTextSize(30);
        mPaint.setColor(Color.BLACK);
        String str1 = "Daniel  Wellington";
        canvas.drawText(str1, width / 2 - 100, width / 2 - DisplayUtil.Dp2Px(getContext(), 50), mPaint);


    }

    /**
     * 绘制时  分 表 指针
     *
     * @param canvas
     */
    private void drawPointer(Canvas canvas) {

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR);
        mMinuate = mCalendar.get(Calendar.MINUTE);
        mSecond = mCalendar.get(Calendar.SECOND);
        //小时的旋转度
        mDegrees = mHour * 30 + mMinuate / 2;
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(mDegrees, width / 2, width / 2);
        canvas.drawLine(width / 2, height / 2-14, width / 2, height / 2 - 34, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        Path path=new Path();
        path.moveTo(width / 2, height / 2 - 34);
        path.lineTo(width / 2-8, height / 2 - 34);
        path.lineTo(width / 2-8, height / 2 - 34-mHourLineLen);
        path.lineTo(width / 2, height / 2 - 34-mHourLineLen-30);
        path.lineTo(width / 2+8, height / 2 - 34-mHourLineLen);
        path.lineTo(width / 2+8, height / 2 - 34);
        path.lineTo(width / 2, height / 2 - 34);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.restore();
        //分钟
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mDegrees = mMinuate * 6 + mSecond / 10;
        canvas.save();
        canvas.rotate(mDegrees, width / 2, width / 2);
        canvas.drawLine(width / 2, height / 2-14, width / 2, width / 2 - 34, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        Path path1=new Path();
        path1.moveTo(width / 2, height / 2 - 34);
        path1.lineTo(width / 2-6, height / 2 - 34);
        path1.lineTo(width / 2-6, height / 2 - 34-mMinuateLine);
        path1.lineTo(width / 2, height / 2 - 34-mMinuateLine-30);
        path1.lineTo(width / 2+6, height / 2 - 34-mMinuateLine);
        path1.lineTo(width / 2+6, height / 2 - 34);
        path1.lineTo(width / 2, height / 2 - 34);
        path1.close();
        canvas.drawPath(path1, mPaint);
        canvas.restore();

    }


}

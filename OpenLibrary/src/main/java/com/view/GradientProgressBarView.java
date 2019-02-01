package com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.utils.DisplayUtil;

/**
 * @author nicestars
 * @time 2019/1/25 16:51.
 * @Description ：圆形渐变进度条逆时针
 */
public class GradientProgressBarView extends View {

    private Paint mPaint;

    private int strokewidth = 10;//进度条宽度
    private int radius ;//圆的直径
    private int startAngle = 90;//进度条开始的角度
    private int diameter ;

    private float progress;//进度

    private int mWidth;
    private int mHeight;

    private String b1;//背景启始颜色
    private String b2;//背景结束颜色

    private String c1;//进度启始颜色
    private String c2;//进度结束颜色

    private String textColor;//文字颜色
    private String text;//绘制的文字
    private int textSize;

    public GradientProgressBarView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);   //设置抗锯齿
    }

    public GradientProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);   //设置抗锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        if (mWidth > mHeight) {
            mWidth = mHeight;
            mHeight = mWidth;
        } else if (mWidth < mHeight) {
            mHeight = mWidth;
            mWidth = mHeight;
        }
        setMeasuredDimension(mWidth, mHeight);
        radius=mWidth/2;
        diameter=mWidth;

    }


    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setAngleShader(String c1, String c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public void setBackgroundShader(String b1, String b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    public void setTextAndColor(String textColor, String text) {
        this.textColor = textColor;
        this.text = text;
    }

    public void setTextSize(int size) {
        this.textSize = DisplayUtil.Dp2Px(getContext(), size);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawStroke(canvas);
        drawText(canvas);
    }


    /**
     * 画个背景渐变圆
     */
    private void drawBackground(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        Shader shader = new LinearGradient(0, radius, diameter, radius, Color.parseColor(b1),
                Color.parseColor(b2), Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        mPaint.setAntiAlias(true);   //设置抗锯齿
        canvas.drawCircle(radius, radius, radius, mPaint);

    }

    /**
     * @param canvas 圆弧
     */
    private void drawStroke(Canvas canvas) {
        int sweepAngle = (int) -(progress * 360);
        mPaint.setStyle(Paint.Style.STROKE);
        float radian = (float) Math.toRadians(sweepAngle + startAngle);
        float x = (float) (radius + Math.cos(radian) * radius);
        float y = (float) (radius + Math.sin(radian) * radius);
        Shader shader = new LinearGradient(radius, diameter, x, y, Color.parseColor(c1),
                Color.parseColor(c2), Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        mPaint.setStrokeWidth(strokewidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        RectF mRectF = new RectF();
        mRectF.left = strokewidth / 2;
        mRectF.right = diameter - strokewidth / 2;
        mRectF.top = strokewidth / 2;
        mRectF.bottom = diameter - strokewidth / 2;
        mPaint.setAntiAlias(true);   //设置抗锯齿
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, mPaint);

    }


    private void drawText(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);

        mPaint.setColor(Color.parseColor(textColor));
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(textSize);
        float textWidth = mPaint.measureText(text);
        // 文字baseline在y轴方向的位置
        float baseLineY = Math.abs(mPaint.ascent() + mPaint.descent()) / 2;
        canvas.drawText(text, -textWidth / 2, baseLineY, mPaint);

    }


}

package com.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircleImageView extends AppCompatImageView {

    private float mWidth;
    private float mHeight;
    private float mRadius;
    private Paint mPaint;
    private Matrix mMatrix;

    private float strokewidth = 10;//边框宽度
    private String c1 = "#E91E63";//渐变颜色1
    private String c2 = "#2196F3";//渐变颜色2

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);   //设置抗锯齿
        mMatrix = new Matrix();      //初始化缩放矩阵
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRadius = Math.min(mWidth, mHeight) / 2;//获取圆的半径
    }


    /**
     * @param px 设置边框宽度
     */
    public void setStrokeWidth(float px) {
        this.strokewidth = px;

    }

    /**
     * @param c1
     * @param c2 设置渐变颜色
     */
    public void setStrokeColor(String c1, String c2) {
        this.c1 = c1;
        this.c2 = c2;

    }


    /**
     * @param canvas 画边框
     */
    private void drawStroke(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor(c1),
                Color.parseColor(c2), Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        mPaint.setStrokeWidth(strokewidth);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius - strokewidth, mPaint);
    }

    /**
     * @param canvas 画头像
     */
    private void drawBitmap(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(initBitmapShader());
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius - (strokewidth + 10), mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawStroke(canvas);
        drawBitmap(canvas);

    }

    private BitmapShader initBitmapShader() {
        try {
            Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();//获取图片设置进来的bitmap对象
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);//新建bitmap shader对象
            float scale = Math.max(mWidth / bitmap.getWidth(), mHeight / bitmap.getHeight());//求缩放比例最大比例
            mMatrix.setScale(scale, scale);//设置缩放比例
            bitmapShader.setLocalMatrix(mMatrix);
            return bitmapShader;
        } catch (NullPointerException e) {
            //bitmap can not be null you, should set bitmap object
            throw new NullPointerException("do not hava set resource");
        }

    }


}

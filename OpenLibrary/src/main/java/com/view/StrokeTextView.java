package com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nicestars on 2018/1/29 15:22.
 * E-mail:nicestars.cn@gmail.com
 * 优化了： https://github.com/xch168/StrokeTextView 的代码 新增了可以使字体变色的需求
 * 感谢 ：XuCanHui  
 */


public class StrokeTextView extends TextView {

    private TextView mOutTextView;

    private int mStrokeColor = Color.BLUE;
    private int mtopColor = Color.BLUE;
    private int mbottomColor = Color.BLUE;
    private float mStrokeWidth = 5F;

    public StrokeTextView(Context context) {
        super(context);
        mOutTextView = new TextView(context);
        init(context, null);
    }

    public StrokeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mOutTextView = new TextView(context, attrs);
        init(context, attrs);
    }

    public StrokeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mOutTextView = new TextView(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        initAttribute(context, attrs);

        TextPaint paint = mOutTextView.getPaint();
        paint.setStrokeWidth(mStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        mOutTextView.setTextColor(mStrokeColor);
        mOutTextView.setGravity(getGravity());
    }

    private void initAttribute(Context context, AttributeSet attrs) {
        if (attrs != null) {
          TypedArray  typedArray = context.obtainStyledAttributes(attrs, com.openlibraryview.R.styleable.CustomStrokeTextView);
            mStrokeColor = typedArray.getColor(com.openlibraryview.R.styleable.CustomStrokeTextView_outlineColor, Color.BLUE);
            mStrokeWidth = typedArray.getDimension(com.openlibraryview.R.styleable.CustomStrokeTextView_outlineStrokeWidth, 5F);
            mtopColor = typedArray.getColor(com.openlibraryview.R.styleable.CustomStrokeTextView_topcolor, 0);
            mbottomColor = typedArray.getColor(com.openlibraryview.R.styleable.CustomStrokeTextView_bottomcolor, 0);
            typedArray.recycle();
        }
    }

    public void setStrokeColor(int colorId) {
        if (mOutTextView != null) {
            mOutTextView.setTextColor(getResources().getColor(colorId));
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence content = mOutTextView.getText();
        if ((content == null) || (!content.equals(getText()))) {
            mOutTextView.setText(getText());
            postInvalidate();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mOutTextView != null) {
            mOutTextView.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mOutTextView.layout(left, top, right, bottom);
        if (changed){
            getPaint().setShader(new LinearGradient(
                    0, 0, 0, getHeight(),
                    mtopColor, mbottomColor, Shader.TileMode.CLAMP));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mOutTextView.draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);
        if (mOutTextView != null) {
            mOutTextView.setGravity(gravity);
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        if (mOutTextView != null) {
            mOutTextView.setLayoutParams(params);
        }
    }

    @Override
    public void setMinimumWidth(int minWidth) {
        super.setMinimumWidth(minWidth);
        if (mOutTextView != null) {
            mOutTextView.setMinWidth(minWidth);
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (mOutTextView != null) {
            mOutTextView.setText(text);
        }
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        if (mOutTextView != null) {
            mOutTextView.setTextSize(size);
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (mOutTextView != null) {
            mOutTextView.setVisibility(visibility);
        }
    }
}
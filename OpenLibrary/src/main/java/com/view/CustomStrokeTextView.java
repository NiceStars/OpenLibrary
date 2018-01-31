package com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openlibraryview.R;


/**
 * Created by nicestars on 2018/1/29 15:22.
 * E-mail:nicestars.cn@gmail.com
 */

public class CustomStrokeTextView extends TextView {
    private TextView textview = null;
    private TypedArray typedArray;

    public CustomStrokeTextView(Context context) {
        super(context);
        textview = new TextView(context);
        typedArray = context.obtainStyledAttributes(R.styleable.CustomStrokeTextView);
        init(typedArray);
    }

    public CustomStrokeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        textview = new TextView(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomStrokeTextView);
        init(typedArray);
    }

    public CustomStrokeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textview = new TextView(context, attrs, defStyleAttr);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomStrokeTextView);
        init(typedArray);
    }


    private void init(TypedArray typedArray) {
        Paint paint = textview.getPaint();
        paint.setStrokeWidth(typedArray.getDimension(R.styleable.CustomStrokeTextView_outlineStrokeWidth, 0));
        paint.setStyle(Paint.Style.STROKE);
        textview.setTextColor(typedArray.getColor(R.styleable.CustomStrokeTextView_outlineColor, 0));
        textview.setGravity(getGravity());


    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        textview.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        CharSequence text = textview.getText();
        if (text == null || !text.equals(this.getText())) {
            textview.setText(getText());
            postInvalidate();
        }
        textview.measure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        textview.layout(top, left, right, bottom);
        if (changed){
            getPaint().setShader(new LinearGradient(
                    0, 0, 0, getHeight(),
                    typedArray.getColor(R.styleable.CustomStrokeTextView_topcolor,0), typedArray.getColor(R.styleable.CustomStrokeTextView_bottomcolor,0), Shader.TileMode.CLAMP));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        textview.draw(canvas);
        super.onDraw(canvas);
    }
}

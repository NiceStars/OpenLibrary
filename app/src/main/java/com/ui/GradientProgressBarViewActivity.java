package com.ui;

import android.content.Context;
import android.content.Intent;

import com.base.BaseActivity;
import com.nsopenlibrary.R;
import com.view.GradientProgressBarView;

/**
 * @author nicestars
 * @time 2019/1/25 17:09.
 * @Description ：
 */
public class GradientProgressBarViewActivity extends BaseActivity {

    private GradientProgressBarView view;

    public static void start(Context context) {
        context.startActivity(new Intent(context, GradientProgressBarViewActivity.class));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_gradientprogressbarview;
    }

    @Override
    public void init() {

        view = findViewById(R.id.view);
        view.setBackgroundShader("#FFD0C4", "#FFECE6");
        view.setAngleShader("#FFBF8D", "#FF7753");
        view.setProgress((float) 0.4);
        view.setTextAndColor("#FF7753", "40%");
        view.setTextSize(15);
    }
}

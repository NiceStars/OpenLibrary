package com.ui;

import android.content.Context;
import android.content.Intent;

import com.base.BaseActivity;
import com.nsopenlibrary.R;

/**
 * create by NiceStars on 2018.09.28
 */

public class StrokeTextViewActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, StrokeTextViewActivity.class));
    }


    @Override
    public int getLayout() {
        return R.layout.activity_stroketextview;
    }

    @Override
    public void init() {

    }
}

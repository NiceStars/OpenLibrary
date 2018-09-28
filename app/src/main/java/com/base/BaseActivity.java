package com.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * create by NiceStars on 2018.09.28
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayout();
    public abstract void init();

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mView = getWindow().getDecorView();
        init();
    }

    protected final <T extends View> T $(@IdRes int id) {

        return mView.findViewById(id);
    }


}

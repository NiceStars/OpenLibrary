package com.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.base.BaseActivity;
import com.nsopenlibrary.R;
import com.view.CircleImageView;

/**
 * create by NiceStars on 2018.09.28
 */

public class CircleImageViewActivity extends BaseActivity {

    public static void start(Context context){
        context.startActivity(new Intent(context, CircleImageViewActivity.class));
    }

    private CircleImageView circleImageView;

    @Override
    public int getLayout() {
        return R.layout.circleimageview_activity;
    }

    @Override
    public void init() {
        circleImageView = $(R.id.cir_view);
        circleImageView.setStrokeWidth(10);
        circleImageView.setStrokeColor("#FF1493", "#FF0000");
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_yfxjj)).getBitmap();
        circleImageView.setImageBitmap(bitmap);
    }
}

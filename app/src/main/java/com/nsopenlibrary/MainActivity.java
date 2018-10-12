package com.nsopenlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ui.AmountActivity;
import com.ui.CircleImageViewActivity;
import com.ui.DWClockViewActivity;
import com.ui.StrokeTextViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mStrokeTextView, mCirCleImageView, mDwClockView, bt_amountView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStrokeTextView = findViewById(R.id.bt_strokeTextView);
        mCirCleImageView = findViewById(R.id.bt_circleImageView);
        mDwClockView = findViewById(R.id.bt_dial);
        bt_amountView = findViewById(R.id.bt_amountView);

        setListener();
    }

    private void setListener() {
        mStrokeTextView.setOnClickListener(this);
        mCirCleImageView.setOnClickListener(this);
        mDwClockView.setOnClickListener(this);
        bt_amountView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_strokeTextView:
                StrokeTextViewActivity.start(this);
                break;
            case R.id.bt_circleImageView:
                CircleImageViewActivity.start(this);
                break;
            case R.id.bt_dial:
                DWClockViewActivity.start(this);
                break;
            case R.id.bt_amountView:
                AmountActivity.start(this);
                break;
        }
    }
}

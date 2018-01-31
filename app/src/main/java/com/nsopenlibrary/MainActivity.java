package com.nsopenlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.view.CustomStrokeTextView;

public class MainActivity extends AppCompatActivity {

    private CustomStrokeTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_game_name1);

        textView.setText("222");
    }
}

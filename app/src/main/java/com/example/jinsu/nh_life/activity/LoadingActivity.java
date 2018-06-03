package com.example.jinsu.nh_life.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jinsu.nh_life.R;


public class LoadingActivity extends AppCompatActivity {
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mHandler = new Handler();
        mHandler.postDelayed(mStartRunnable, 800);
    }

    private Runnable mStartRunnable = () -> {
        // TODO: 2018-05-26 자동로그인 기능 구현
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    };

    @Override
    public void finish() {
        mHandler.removeCallbacks(mStartRunnable);
        super.finish();
    }
}

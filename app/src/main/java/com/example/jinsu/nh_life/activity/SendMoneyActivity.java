package com.example.jinsu.nh_life.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jinsu.nh_life.R;

public class SendMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
            finish();
    }
}

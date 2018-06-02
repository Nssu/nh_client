package com.example.jinsu.nh_life.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendMoneyActivity extends AppCompatActivity {

    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.text_point)
    TextView textPoint;
    @BindView(R.id.point)
    TextView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        ButterKnife.bind(this);

        point.setText(""+ Constants.point+"P");

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                textPoint.setText("" + progress);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

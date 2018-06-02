package com.example.jinsu.nh_life.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.network.RetroClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMoneyActivity extends AppCompatActivity {

    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.text_point)
    TextView textPoint;
    @BindView(R.id.send_edit_account)
    EditText sendEditAccount;
    @BindView(R.id.send_btn_ok)
    Button sendBtnOk;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    @BindView(R.id.send_btn_my)
    Button sendBtnMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        ButterKnife.bind(this);

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

        sendBtnOk.setOnClickListener(v ->
        {
            retrofit2.Call<String> call = RetroClient.getInstance().getRetroService().sendMoney("1", Constants.getInstance().getUSER_KEY());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("send_","연결 성공");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("send_","연결 실패 : " + t.getMessage());
                }
            });

        });

        sendBtnMy.setOnClickListener(v ->
        {
            sendEditAccount.setText("00820-11145-57014-65501-45550-4371");
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

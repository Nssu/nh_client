package com.example.jinsu.nh_life.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.jinsu.nh_life.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialogUserInfo extends Dialog {

    @BindView(R.id.group_gender)
    RadioGroup groupGender;
    @BindView(R.id.spinner_age)
    Spinner spinnerAge;
    @BindView(R.id.checkbox1)
    CheckBox checkbox1;
    @BindView(R.id.checkbox2)
    CheckBox checkbox2;
    @BindView(R.id.checkbox3)
    CheckBox checkbox3;

    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;
    @BindView(R.id.button_submit)
    Button buttonSubmit;


    public CustomDialogUserInfo(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_user_info);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButterKnife.bind(this);

        groupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_female:
                        Log.e("testGender", "female");
                        break;
                    case R.id.radio_male:
                        Log.e("testGender", "male");
                        break;
                }
            }
        });
        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("testAge", "" + parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @OnClick({R.id.checkbox1, R.id.checkbox2, R.id.checkbox3,R.id.button_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox1:
                if (checkbox1.isChecked()) {
                    Log.e("testCheckbox", "1");
                }
                break;
            case R.id.checkbox2:
                if (checkbox2.isChecked()) {
                    Log.e("testCheckbox", "2");
                }
                break;
            case R.id.checkbox3:
                if (checkbox3.isChecked()) {
                    Log.e("testCheckbox", "3");
                }
                break;
            case R.id.button_submit:
                //서버통신
                dismiss();
                break;
        }
    }

}

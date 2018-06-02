package com.example.jinsu.nh_life.common;

import com.example.jinsu.nh_life.model.Coupon;

import java.util.ArrayList;

public class Constants {
    private static Constants instance= null;
    private int Step = 0;
    private String PREF_LOCAL_ID = "pref_id";
    private String PREF_USER_KEY = "user_key";

    private String USER_KEY = "user_1";

    private ArrayList<Coupon> list_coupons = new ArrayList<>();
    public String[] week = {"월요일","화요일","수요일","목요일","금요일","토요일","일요일"};
    private long Time = 0;

    private Constants(){}

    public static Constants getInstance()
    {
        if(instance == null)
        {
            instance = new Constants();
        }
        return instance;
    }

    public int getStep() {
        return Step;
    }

    public String getPREF_LOCAL_ID() {
        return PREF_LOCAL_ID;
    }

    public String getPREF_USER_KEY() {
        return PREF_USER_KEY;
    }

    public void setStep(int step) {
        Step = step;
    }

    public String getUSER_KEY() {
        return USER_KEY;
    }

    public ArrayList<Coupon> getList_coupons() {
        return list_coupons;
    }

    public void setList_coupons(ArrayList<Coupon> list_coupons) {
        this.list_coupons = list_coupons;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }
}

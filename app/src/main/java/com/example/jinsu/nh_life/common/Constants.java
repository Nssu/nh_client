package com.example.jinsu.nh_life.common;

import com.example.jinsu.nh_life.model.Coupon;

public class Constants {
    private static Constants instance= null;
    private int Step = 0;
    public static int target = 10;
    public static double one_km = 0.0005;
    public static double one_kcal = 0.05;
    private String PREF_LOCAL_ID = "pref_id";
    private String PREF_USER_KEY = "user_key";
    private boolean DELETE_STATUS = true;
    public static int point = 2000;


    private String USER_KEY = "user_1";

    private Coupon REC_COUPON;
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

    public Coupon getREC_COUPON() {
        return REC_COUPON;
    }

    public void setREC_COUPON(Coupon REC_COUPON) {
        this.REC_COUPON = REC_COUPON;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public boolean isDELETE_STATUS() {
        return DELETE_STATUS;
    }

    public void setDELETE_STATUS(boolean DELETE_STATUS) {
        this.DELETE_STATUS = DELETE_STATUS;
    }

}

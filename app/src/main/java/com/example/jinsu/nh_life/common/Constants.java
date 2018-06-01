package com.example.jinsu.nh_life.common;

public class Constants {
    private static Constants instance= null;
    private int Step = 0;
    private long Time = 0;
    public String[] week = {"월요일","화요일","수요일","목요일","금요일","토요일","일요일"};
    public String PREF_LOCAL_ID = "pref_id";
    public String PREF_USER_KEY = "user_key";


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

    public long getTime(){
        return Time;

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

    public void setTime(long time){Time = time;}
}

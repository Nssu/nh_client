package com.example.jinsu.nh_life.model;

public class Week {
    private String day;
    private int step_num;
    private int kcal;
    private int km;
    private String time;

    public Week(String day, int step_num, int kcal, int km, String time) {
        this.day = day;
        this.step_num = step_num;
        this.kcal = kcal;
        this.km = km;
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public int getStep_num() {
        return step_num;
    }

    public int getKcal() {
        return kcal;
    }

    public int getKm() {
        return km;
    }

    public String getTime() {
        return time;
    }

}

package com.example.jinsu.nh_life.model;

public class User {
    private String user_key;
    private String user_name;
    private String user_image;
    private String sex;
    private int user_age;
    private int user_point;

    public User(String user_key, String user_name, String user_image, String sex, int user_age, int user_point) {
        this.user_key = user_key;
        this.user_name = user_name;
        this.user_image = user_image;
        this.sex = sex;
        this.user_age = user_age;
        this.user_point = user_point;
    }

    public String getUser_key() {
        return user_key;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public String getSex() {
        return sex;
    }

    public int getUser_age() {
        return user_age;
    }

    public int getUser_point() {
        return user_point;
    }
}

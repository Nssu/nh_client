package com.example.jinsu.nh_life.model;

public class Coupon {
    private int coupon_key;
    private String coupon_image;
    private String coupon_category;
    private String coupon_brand;
    private String coupon_content;
    private String coupon_start_date;
    private String coupon_expired_date;

    public Coupon(int coupon_key, String coupon_image, String coupon_category, String coupon_brand, String coupon_content, String coupon_start_date, String coupon_expired_date) {
        this.coupon_key = coupon_key;
        this.coupon_image = coupon_image;
        this.coupon_category = coupon_category;
        this.coupon_brand = coupon_brand;
        this.coupon_content = coupon_content;
        this.coupon_start_date = coupon_start_date;
        this.coupon_expired_date = coupon_expired_date;
    }

    public int getCoupon_key() {
        return coupon_key;
    }

    public String getCoupon_image() {
        return coupon_image;
    }

    public String getCoupon_category() {
        return coupon_category;
    }

    public String getCoupon_brand() {
        return coupon_brand;
    }

    public String getCoupon_content() {
        return coupon_content;
    }

    public String getCoupon_start_date() {
        return coupon_start_date;
    }

    public String getCoupon_expired_date() {
        return coupon_expired_date;
    }


}

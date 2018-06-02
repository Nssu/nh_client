package com.example.jinsu.nh_life.model;

public class Point {
    private String point_date;
    private String point_brand;
    private int point_price;

    public Point(String point_date, String point_brand, int point_price) {
        this.point_date = point_date;
        this.point_brand = point_brand;
        this.point_price = point_price;
    }

    public String getPoint_date() {
        return point_date;
    }

    public String getPoint_brand() {
        return point_brand;
    }

    public int getPoint_price() {
        return point_price;
    }
}

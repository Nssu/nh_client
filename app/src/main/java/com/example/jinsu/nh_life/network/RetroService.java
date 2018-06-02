package com.example.jinsu.nh_life.network;

import com.example.jinsu.nh_life.model.Coupon;
import com.example.jinsu.nh_life.model.MyRank;
import com.example.jinsu.nh_life.model.Point;
import com.example.jinsu.nh_life.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroService {

    @GET("/userinfomation/")
    Call<User> getUser(@Query("user_key") String user_key);

    @FormUrlEncoded
    @POST("/postUserInterest/")
    Call<String> postInterest(@Field("user_key") int user_key, @Field("interest1") String interest1, @Field("interest2")
                             String interest2, @Field("interest1") String interest3,
                             @Field("interest1") String interest4,@Field("interest1") String interest5);


    @GET("/getPoint/")
    Call<ArrayList<Point>> getPointList(@Query("user_key") String user_key);

    @FormUrlEncoded
    @POST("/postMyCoupon/")
    Call<Integer>  postCoupon(@Field("user_key") String user_key, @Field("coupon_key") int coupon_key);

    @GET("/getMyCoupon/")
    Call<ArrayList<Coupon>> getMyCoupon(@Query("user_key") String user_key);

    @GET
    Call<ArrayList<Coupon>> getAllCoupon();

    @GET
    Call<MyRank> getRank(@Query("user_key") String user_key);



}

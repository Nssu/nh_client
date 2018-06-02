package com.example.jinsu.nh_life.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.model.Coupon;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CouponFragment extends Fragment {
    @BindView(R.id.main_im_coupon)
    ImageView mainImCoupon;
    @BindView(R.id.main_btn_receive_coupon)
    Button mainBtnReceiveCoupon;
    Unbinder unbinder;
    private ArrayList<Coupon> list_coupons;

    public CouponFragment() {
        this.list_coupons = Constants.getInstance().getList_coupons();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_main_coupon, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mainImCoupon.setImageResource(R.drawable.barcode);
        mainBtnReceiveCoupon.setOnClickListener(v ->
        {

        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

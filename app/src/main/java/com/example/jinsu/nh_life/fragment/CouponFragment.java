package com.example.jinsu.nh_life.fragment;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.activity.AllCouponListActivity;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.model.Coupon;
import com.example.jinsu.nh_life.network.RetroClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponFragment extends Fragment {
    @BindView(R.id.main_im_coupon)
    ImageView mainImCoupon;
    @BindView(R.id.main_btn_receive_coupon)
    Button mainBtnReceiveCoupon;
    Unbinder unbinder;
    @BindView(R.id.main_btn_more)
    Button mainBtnMore;
    @BindView(R.id.main_txt_content)
    TextView mainTxtContent;
    @BindView(R.id.main_txt_brand)
    TextView mainTxtBrand;
    @BindView(R.id.main_txt_date)
    TextView mainTxtDate;
    private Coupon coupon;
    private boolean hint = false;

    public CouponFragment() {
//        this.coupon = Constants.getInstance().getREC_COUPON();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_main_coupon, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        Log.d("main_activity","onCreateVIew()");

        if(hint) {
            setVIew();
        }
        mainBtnMore.setOnClickListener(v ->
        {
            startActivity(new Intent(getContext(), AllCouponListActivity.class));
        });

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("main_activity","onResume()");

    }


    private void setVIew() {
        Log.d("main_activity","setVIew()");
        coupon = Constants.getInstance().getREC_COUPON();

        if(coupon != null) {
            mainBtnReceiveCoupon.setOnClickListener(v ->
            {
                Call<String> call = RetroClient.getInstance()
                        .getRetroService().postCoupon(Constants.getInstance().getUSER_KEY(),coupon.getCoupon_key());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String res = response.body();
                        if(res.equals("-1"))
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                            alert.setMessage("이미 쿠폰이 5개입니다.")
                                    .setCancelable(false)
                                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            return;
                                        }
                                    }).show();
                        }
                        else if(res.equals("-2"))
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                            alert.setMessage("이미 가지고 있습니다.")
                                    .setCancelable(false)
                                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            return;
                                        }
                                    }).show();
                        }
                        else
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                            alert.setMessage("쿠폰 받았습니다!!!")
                                    .setCancelable(false)
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            return;
                                        }
                                    }).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            });

            Glide.with(this).load(RetroClient.getInstance().getBASE_URL() + coupon.getCoupon_image()).into(mainImCoupon);
            mainTxtBrand.setText(coupon.getCoupon_brand());
            mainTxtContent.setText(coupon.getCoupon_content());
            String start_date = coupon.getCoupon_start_date().substring(0, 10);
            String expired_date = coupon.getCoupon_expired_date().substring(0, 10);
            start_date = start_date.replaceAll("-", ".");
            expired_date = expired_date.replaceAll("-", ".");
            mainTxtDate.setText(start_date + " ~ " + expired_date);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        //화면이 실제로 보일 떄
        if(isVisibleToUser)
        {
            hint = true;
            Log.d("main_activity","실제로 보일 때");
            if(getActivity() != null)
            {
                Log.d("main_activity","실제로 보일 때 + null아닐 떄");
                setVIew();
            }
        }

    }
}

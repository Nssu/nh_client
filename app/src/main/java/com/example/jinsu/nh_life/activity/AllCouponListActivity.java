package com.example.jinsu.nh_life.activity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.adapter.AllCouponListAdapter;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.model.Coupon;
import com.example.jinsu.nh_life.network.RetroClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCouponListActivity extends AppCompatActivity {

    @BindView(R.id.all_coupon_recycle)
    RecyclerView allCouponRecycle;

    private ArrayList<Coupon> coupon_list;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_coupon_list);
        ButterKnife.bind(this);
        initRecyclerVIew();


    }

    private void initRecyclerVIew() {
        coupon_list = new ArrayList<>();
        mAdapter = new AllCouponListAdapter(this, coupon_list, new AllCouponListAdapter.onPostCouponCallback() {
            @Override
            public void onPost(int position) {
                Call<String> call = RetroClient.getInstance()
                        .getRetroService().postCoupon(Constants.getInstance().getUSER_KEY(),coupon_list.get(position).getCoupon_key());
                Log.d("all_coupon",coupon_list.get(position).getCoupon_key() + " / " +position);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response)
                    {
                        Log.d("all_coupon","연결 성공 : " + response.body().toString());
                        String res = response.body();
                        if(res.equals("-1"))
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(AllCouponListActivity.this);
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
                            AlertDialog.Builder alert = new AlertDialog.Builder(AllCouponListActivity.this);
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
                            AlertDialog.Builder alert = new AlertDialog.Builder(AllCouponListActivity.this);
                            alert.setMessage("쿠폰 받았습니다!!!")
                                    .setCancelable(false)
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    }).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("all_coupon","연결 실패 : " + t.getMessage());
                    }
                });
            }
        });

        allCouponRecycle.setHasFixedSize(true);
        allCouponRecycle.setLayoutManager(new LinearLayoutManager(this));
        allCouponRecycle.setAdapter(mAdapter);
        setList();
    }


    private void setList() {
        coupon_list.clear();
        Call<ArrayList<Coupon>> call = RetroClient.getInstance().getRetroService().getAllCoupon();

        call.enqueue(new Callback<ArrayList<Coupon>>() {
            @Override
            public void onResponse(Call<ArrayList<Coupon>> call, Response<ArrayList<Coupon>> response) {
                Log.d("my_coupon_list", "연결 성공 : " + response.body().get(0).getCoupon_brand());
                coupon_list.addAll(response.body());
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<Coupon>> call, Throwable t) {
                Log.d("my_coupon_list", "연결 실패 : " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}


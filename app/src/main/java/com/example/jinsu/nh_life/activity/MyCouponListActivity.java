package com.example.jinsu.nh_life.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.adapter.MyCouponListAdapter;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.model.Coupon;
import com.example.jinsu.nh_life.network.RetroClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCouponListActivity extends AppCompatActivity {

    @BindView(R.id.my_coupon_recycle)
    RecyclerView myCouponRecycle;
    @BindView(R.id.my_coupon_btn_delete)
    Button myCouponBtnDelete;
    @BindView(R.id.my_coupon_btn_more)
    Button myCouponBtnMore;

    private ArrayList<Coupon> coupon_list;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon_list);
        ButterKnife.bind(this);
        initRecyclerVIew();
        initListener();

    }

    private void initRecyclerVIew() {
        coupon_list = new ArrayList<>();
        mAdapter = new MyCouponListAdapter(this, coupon_list, new MyCouponListAdapter.onDeleteCallback() {
            @Override
            public void onDelete(int position) {
                Call<String> call = RetroClient.getInstance().getRetroService().deleteCoupon(Constants.getInstance().getUSER_KEY(),coupon_list.get(position).getCoupon_key());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("my_coupon_list","연결 성공" + coupon_list.get(position).getCoupon_key());
                        Constants.getInstance().setDELETE_STATUS(true);
                        coupon_list.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("my_coupon_list","연결 실패 : " + t.getMessage() + " / " + coupon_list.get(position).getCoupon_key());
                    }
                });
            }
        });
        myCouponRecycle.setHasFixedSize(true);
        myCouponRecycle.setLayoutManager(new LinearLayoutManager(this));
        myCouponRecycle.setAdapter(mAdapter);
        setList();
    }

    private void setList() {
        coupon_list.clear();
        Call<ArrayList<Coupon>> call = RetroClient.getInstance().getRetroService().getMyCoupon(Constants.getInstance().getUSER_KEY());

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

    private void initListener()
    {
        myCouponBtnDelete.setOnClickListener(v ->
        {
            Constants.getInstance().setDELETE_STATUS(false);
            mAdapter.notifyDataSetChanged();
        });
        myCouponBtnMore.setOnClickListener(v ->
        {
            startActivity(new Intent(this,AllCouponListActivity.class));
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}

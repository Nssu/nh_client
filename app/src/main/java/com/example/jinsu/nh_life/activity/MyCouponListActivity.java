package com.example.jinsu.nh_life.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    private ArrayList<Coupon> coupon_list;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon_list);
        ButterKnife.bind(this);
        initRecyclerVIew();

    }

    private void initRecyclerVIew()
    {
        coupon_list = new ArrayList<>();
        mAdapter = new MyCouponListAdapter(this, coupon_list);
        myCouponRecycle.setHasFixedSize(true);
        myCouponRecycle.setLayoutManager(new LinearLayoutManager(this));
        myCouponRecycle.setAdapter(mAdapter);
        setList();
    }

    private void setList()
    {
        coupon_list.clear();
        Call<ArrayList<Coupon>> call =  RetroClient.getInstance().getRetroService().getMyCoupon(Constants.getInstance().getUSER_KEY());

        call.enqueue(new Callback<ArrayList<Coupon>>() {
            @Override
            public void onResponse(Call<ArrayList<Coupon>> call, Response<ArrayList<Coupon>> response) {
                Log.d("my_coupon_list","연결 성공 : " + response.body().get(0).getCoupon_brand());
                coupon_list.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Coupon>> call, Throwable t) {
                Log.d("my_coupon_list","연결 실패 : " + t.getMessage());
            }
        });


    }

}

package com.example.jinsu.nh_life.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.adapter.PointListAdapter;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.model.Point;
import com.example.jinsu.nh_life.network.RetroClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointListActivity extends AppCompatActivity {


    @BindView(R.id.point_list_recycle)
    RecyclerView pointListRecycle;
    @BindView(R.id.point_list_txt_total_point)
    TextView pointListTxtTotalPoint;

    private ArrayList<Point> point_list;
    private RecyclerView.Adapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_list);
        ButterKnife.bind(this);
        pointListTxtTotalPoint.setText(""+Constants.point+"P");
        initRecyclerview();
    }

    private void initRecyclerview() {

        point_list = new ArrayList<>();
        mAdapter = new PointListAdapter(this, point_list);
        pointListRecycle.setHasFixedSize(true);
        pointListRecycle.setLayoutManager(new LinearLayoutManager(this));
        pointListRecycle.setAdapter(mAdapter);
        setList();
    }

    private void setList() {
        point_list.clear();
        Call<ArrayList<Point>> call = RetroClient.getInstance().getRetroService().getPointList(Constants.getInstance().getUSER_KEY());

        call.enqueue(new Callback<ArrayList<Point>>() {
            @Override
            public void onResponse(Call<ArrayList<Point>> call, Response<ArrayList<Point>> response) {
                Log.d("point_list", "연결 성공" + response.body().get(0).getPoint_brand());
                point_list.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Point>> call, Throwable t) {
                Log.d("point_list", "연결 실패 : " + t.getMessage());
            }
        });


    }


}

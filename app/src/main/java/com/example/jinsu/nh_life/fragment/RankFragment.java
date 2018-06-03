package com.example.jinsu.nh_life.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinsu.nh_life.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RankFragment extends Fragment {

    @BindView(R.id.drawer_im_user)
    RoundedImageView drawerImUser;
    @BindView(R.id.text_step)
    TextView textStep;
    @BindView(R.id.text_rank)
    TextView textRank;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    Unbinder unbinder;
    @BindView(R.id.im_min)
    RoundedImageView imMin;
    @BindView(R.id.im_yoon)
    RoundedImageView imYoon;
    @BindView(R.id.im_sion)
    RoundedImageView imSion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_tab_rank, container, false);



        unbinder = ButterKnife.bind(this, view);
        Glide.with(this).load(R.drawable.my).into(drawerImUser);
        Glide.with(this).load(R.drawable.min).into(imMin);
        Glide.with(this).load(R.drawable.bakery).into(imYoon);
        Glide.with(this).load(R.drawable.sion).into(imSion);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

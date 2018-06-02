package com.example.jinsu.nh_life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.jinsu.nh_life.fragment.CouponFragment;

public class MainPageAdapter extends FragmentStatePagerAdapter {
    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
                return new CouponFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }


}

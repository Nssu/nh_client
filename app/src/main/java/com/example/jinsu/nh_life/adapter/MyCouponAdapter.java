package com.example.jinsu.nh_life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.jinsu.nh_life.fragment.MyCouponFragment;

public class MyCouponAdapter extends FragmentStatePagerAdapter {
    public MyCouponAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
                return new MyCouponFragment(String.valueOf(position));

    }

    @Override
    public int getCount() {
        return 5;
    }


}

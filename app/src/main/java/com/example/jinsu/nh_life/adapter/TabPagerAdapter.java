package com.example.jinsu.nh_life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.jinsu.nh_life.fragment.MissionFragment;
import com.example.jinsu.nh_life.fragment.RankFragment;
import com.example.jinsu.nh_life.fragment.StatFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                StatFragment statFragment = new StatFragment();
                return statFragment;
            case 1:
                RankFragment rankFragment = new RankFragment();
                return rankFragment;
            case 2:
                MissionFragment missionFragment = new MissionFragment();
                return missionFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }


}



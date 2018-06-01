package com.example.jinsu.nh_life.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jinsu.nh_life.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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


    public static class StatFragment extends Fragment {

        @BindView(R.id.chart)
        LineChart lineChart;
        Unbinder unbinder;
        @BindView(R.id.button_previous)
        ImageButton buttonPrevious;
        @BindView(R.id.textview_day)
        TextView textviewDay;
        @BindView(R.id.button_after)
        ImageButton buttonAfter;

        private String[] current_day;
        private int mode; // 0 : 일별, 1: 주별, 2 : 월별

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = (View) inflater.inflate(R.layout.fragment_tab_stat, container, false);
            unbinder = ButterKnife.bind(this, view);

            mode = 0;

            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            current_day = sdf.format(date).split("-");
            textviewDay.setText(current_day[1]+"월 "+current_day[2]+"일");

            ArrayList<Entry> entries = new ArrayList<>();
            entries.add(new Entry(0, 0));
            entries.add(new Entry(2, 2));
            entries.add(new Entry(4, 6));
            entries.add(new Entry(6, 3));
            entries.add(new Entry(8, 4));
            entries.add(new Entry(10, 5));
            entries.add(new Entry(12, 6));

            LineDataSet dataset = new LineDataSet(entries, "걸음 수");

            LineData data = new LineData(dataset);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextColor(Color.BLACK);

            YAxis yRAxis = lineChart.getAxisRight();
            yRAxis.setDrawLabels(false);
            yRAxis.setDrawAxisLine(false);
            yRAxis.setDrawGridLines(false);

            dataset.setDrawFilled(true); //그래프 밑부분 색칠*/

            lineChart.setData(data);
            lineChart.animateY(1000);

            return view;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            unbinder.unbind();
        }

        @OnClick({R.id.button_previous, R.id.button_after})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.button_previous:
                    if(mode == 0) {
                        int temp = Integer.parseInt(current_day[2])-1;
                        textviewDay.setText(current_day[1]+"월 "+temp+"일");
                        current_day[2] = String.valueOf(temp);
                    }
                    break;
                case R.id.button_after:
                    if(mode == 0){
                        int temp = Integer.parseInt(current_day[2])+1;
                        textviewDay.setText(current_day[1]+"월 "+temp+"일");
                        current_day[2] = String.valueOf(temp);
                    }
                    break;
            }
        }
    }

    public static class RankFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = (View) inflater.inflate(R.layout.fragment_tab_rank, container, false);

            return view;
        }
    }

    public static class MissionFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = (View) inflater.inflate(R.layout.fragment_tab_mission, container, false);

            return view;
        }
    }

}



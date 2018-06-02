package com.example.jinsu.nh_life.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.service.StepCheckService;
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

public class StatFragment extends Fragment {

    @BindView(R.id.chart)
    LineChart lineChart;
    Unbinder unbinder;
    @BindView(R.id.button_previous)
    Button buttonPrevious;
    @BindView(R.id.textview_day)
    TextView textviewDay;
    @BindView(R.id.button_after)
    Button buttonAfter;
    @BindView(R.id.textview_step)
    TextView textviewStep;
    @BindView(R.id.textview_kcal)
    TextView textviewKcal;
    @BindView(R.id.textview_distance)
    TextView textviewDistance;
    @BindView(R.id.textview_time)
    TextView textviewTime;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    @BindView(R.id.stat_im_day)
    ImageView statImDay;
    @BindView(R.id.stat_im_week)
    ImageView statImWeek;
    @BindView(R.id.stat_im_month)
    ImageView statImMonth;

    @BindView(R.id.textview_cal)
    TextView textkcal;
    @BindView(R.id.statImPizza)
    ImageView statImPizza;


    private String[] current_day;
    private int mode; // 0 : 일별, 1: 주별, 2 : 월별

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_tab_stat, container, false);
        unbinder = ButterKnife.bind(this, view);

        mode = 0;

        textviewStep.setText(String.valueOf(StepCheckService.getStep()));
        textkcal.setText("" + Math.round(StepCheckService.getStep() * 0.0005 * 100d) / 100d + "kcal");
        textviewTime.setText("" + String.valueOf(StepCheckService.getTime() / 60000) + "분");
        textviewDistance.setText("" + Math.round(StepCheckService.getStep() * 0.05 * 100d) / 100d + "km");

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        current_day = sdf.format(date).split("-");
        textviewDay.setText(current_day[1] + "월 " + current_day[2] + "일");
        Glide.with(this).load(R.drawable.pizza).into(statImPizza);
        Glide.with(this).load(R.drawable.daily).into(statImDay);
        Glide.with(this).load(R.drawable.weekly).into(statImWeek);
        Glide.with(this).load(R.drawable.monthly).into(statImMonth);
        textviewStep.setText(String.valueOf(Constants.getInstance().getStep()));
        textviewTime.setText(String.valueOf(Constants.getInstance().getTime()));


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
        //   lineChart.animateY(1000);

        return view;
    }

    private void setChart() {

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
                if (mode == 0) {
                    int temp = Integer.parseInt(current_day[2]) - 1;
                    textviewDay.setText(current_day[1] + "월 " + temp + "일");
                    current_day[2] = String.valueOf(temp);
                }
                break;
            case R.id.button_after:
                if (mode == 0) {
                    int temp = Integer.parseInt(current_day[2]) + 1;
                    textviewDay.setText(current_day[1] + "월 " + temp + "일");
                    current_day[2] = String.valueOf(temp);
                }
                break;
        }
    }
}

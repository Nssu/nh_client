package com.example.jinsu.nh_life.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.adapter.MainPageAdapter;
import com.example.jinsu.nh_life.service.StepCheckService;
import com.example.jinsu.nh_life.util.CircleAnimation;
import com.example.jinsu.nh_life.util.IndicatorAnimation;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.test_time)
    TextView testTime;
    @BindView(R.id.c)
    ImageView c;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingLayout;
    private Intent manboService;

    private BroadcastReceiver receiver;
    private List<String> numberList;


    private boolean flag = true;
    private String serviceData;
    private String serviceDataTime;
    private IndicatorAnimation circleAnimIndicator;

    @BindView(R.id.test_txt)
    TextView testTxt;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_main)
    DrawerLayout layoutMain;
    @BindView(R.id.nv_admin)
    NavigationView nvAdmin;
    @BindView(R.id.main_layout_circle)
    FrameLayout mainLayoutCircle;
    @BindView(R.id.main_viewpager_coupon)
    ViewPager mainViewpagerCoupon;
    @BindView(R.id.main_indicator)
    IndicatorAnimation mainIndicator;
    @BindView(R.id.main_view_circle)
    CircleView mainViewCircle;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initActivity();
        initService();
        initViewPager();
    }

    public void initActivity() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        actionBar.setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layoutMain, toolbar, 0, 0);
        layoutMain.addDrawerListener(toggle);
        toggle.syncState();

        CircleAnimation animation = new CircleAnimation(mainViewCircle, 260);
        animation.setDuration(10000);
        mainViewCircle.startAnimation(animation);
        // nvAdmin.setNavigationItemSelectedListener(this);


    }

    public void initService() {
        manboService = new Intent(this, StepCheckService.class);
        receiver = new PlayingReceiver();

        try {
            IntentFilter mainFilter = new IntentFilter("make.a.yong.manbo");
            registerReceiver(receiver, mainFilter);
            startService(manboService);

        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();

        }
    }

    public void initViewPager() {

        MainPageAdapter viewPagerAdapter = new MainPageAdapter(getSupportFragmentManager());
        mainViewpagerCoupon.setAdapter(viewPagerAdapter);
        mainViewpagerCoupon.addOnPageChangeListener(mOnPageChangeListener);
        //Indicator 초기화
        initIndicaotor();

    }

    private void initIndicaotor() {

        //원사이의 간격
        mainIndicator.setItemMargin(15);
        //애니메이션 속도
        mainIndicator.setAnimDuration(300);
        //indecator 생성
        mainIndicator.createDotPanel(5, R.drawable.z1, R.drawable.z2);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mainIndicator.selectDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    @Override
    public void onBackPressed() {
        if (layoutMain.isDrawerOpen(GravityCompat.START)) {
            layoutMain.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        testTxt.setText(String.valueOf(StepCheckService.getStep()));
        testTime.setText(String.valueOf(StepCheckService.getTime() / 1000));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navi_coupon: {
                startActivity(new Intent(this, MyCouponListActivity.class));
                break;
            }
        }
        layoutMain.closeDrawer(GravityCompat.START);
        return true;
    }

    class PlayingReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("PlayignReceiver", "IN");
            serviceData = intent.getStringExtra("stepService");
            testTxt.setText(serviceData);
            serviceDataTime = intent.getStringExtra("timeService");
            testTime.setText(serviceDataTime);
        }
    }


}

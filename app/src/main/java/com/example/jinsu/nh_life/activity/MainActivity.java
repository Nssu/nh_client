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
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.adapter.MainPageAdapter;
import com.example.jinsu.nh_life.adapter.MyCouponAdapter;
import com.example.jinsu.nh_life.common.Constants;

import com.example.jinsu.nh_life.model.Coupon;
import com.example.jinsu.nh_life.network.RetroClient;

import com.example.jinsu.nh_life.service.StepCheckService;
import com.example.jinsu.nh_life.util.CircleAnimation;
import com.example.jinsu.nh_life.util.IndicatorAnimation;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_menu)
    ImageView drawerMenu;
    @BindView(R.id.text_point)
    TextView textPoint;
    @BindView(R.id.circle_stat1)
    LinearLayout circleStat1;
    @BindView(R.id.circle_stat2)
    LinearLayout circleStat2;
    @BindView(R.id.layout_stepinfo)
    RelativeLayout layoutStepinfo;
    @BindView(R.id.send_point)
    Button sendPoint;
    @BindView(R.id.shopping)
    Button shopping;
    @BindView(R.id.drawer_im_user)
    RoundedImageView drawerImUser;
    @BindView(R.id.drawer_txt_point)
    TextView drawerTxtPoint;
    @BindView(R.id.drawer_btn_profile)
    Button drawerBtnProfile;
    @BindView(R.id.drawer_btn_coupon)
    Button drawerBtnCoupon;
    @BindView(R.id.drawer_btn_point)
    Button drawerBtnPoint;
    @BindView(R.id.drawer_btn_rating)
    Button drawerBtnRating;
    @BindView(R.id.drawer_btn_logout)
    Button drawerBtnLogout;
    @BindView(R.id.drawer_btn_license)
    Button drawerBtnLicense;
    @BindView(R.id.layout_drawer)
    LinearLayout layoutDrawer;
    @BindView(R.id.frame_step)
    FrameLayout frameStep;
    private Intent manboService;

    private BroadcastReceiver receiver;
    private List<String> numberList;


    private boolean flag = true;
    private String serviceData;
    private String serviceDataTime;
    private IndicatorAnimation circleAnimIndicator;
    private ArrayList<Coupon> coupons_list;

    @BindView(R.id.test_txt)
    TextView testTxt;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_main)
    DrawerLayout layoutMain;
    @BindView(R.id.test_time)
    TextView testTime;
    @BindView(R.id.c)
    ImageView c;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingLayout;
    @BindView(R.id.main_layout_circle)
    FrameLayout mainLayoutCircle;
    @BindView(R.id.main_viewpager_coupon)
    ViewPager mainViewpagerCoupon;
    @BindView(R.id.main_indicator)
    IndicatorAnimation mainIndicator;
    @BindView(R.id.main_view_circle)
    CircleView mainViewCircle;
    @BindView(R.id.viewpager_my_coupon)
    ViewPager viewpagerMyCoupon;
    @BindView(R.id.text_km)
    TextView textKm;
    @BindView(R.id.text_kcal)
    TextView textKcal;




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        coupons_list = new ArrayList<>();
        getData();
        initActivity();
        initService();
        initListener();

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

        MyCouponAdapter myCouponAdapter = new MyCouponAdapter(getSupportFragmentManager());
        viewpagerMyCoupon.setAdapter(myCouponAdapter);
        viewpagerMyCoupon.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Indicator 초기화
        initIndicaotor();

    }

    private void initIndicaotor() {

        //원사이의 간격
        mainIndicator.setItemMargin(10);
        //애니메이션 속도
        mainIndicator.setAnimDuration(400);
        //indecator 생성
        mainIndicator.createDotPanel(5, R.drawable.indicator_non, R.drawable.indicator_select);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mainIndicator.selectDot(position);
            Log.d("main_activity","selected position : " + position);
            Constants.getInstance().setREC_COUPON(coupons_list.get(position));
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

        textKm.setText(""+StepCheckService.getStep() * Constants.one_km);
        textKcal.setText(""+StepCheckService.getStep() * Constants.one_kcal);
        testTime.setText(String.valueOf(StepCheckService.getTime() / 60000));

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

    public void initListener() {
        drawerBtnCoupon.setOnClickListener(v ->
        {
            startActivity(new Intent(this, MyCouponListActivity.class));
        });
        drawerBtnPoint.setOnClickListener(v ->
        {
            startActivity(new Intent(this, PointListActivity.class));
        });
        shopping.setOnClickListener(v ->
        {
            startActivity(new Intent(this,ShopActivity.class));
        });

    }




    @OnClick({R.id.drawer_menu,R.id.frame_step,R.id.send_point})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.drawer_menu:
                layoutMain.openDrawer(GravityCompat.START);
                break;
            case R.id.frame_step:
                startActivity(new Intent(getApplicationContext(),HealthActivity.class));
                finish();
                break;
            case R.id.send_point:
                startActivity(new Intent(getApplicationContext(),SendMoneyActivity.class));
                break;
        }

    }


    private void getData()
    {
        coupons_list.clear();
        Call<ArrayList<Coupon>> call = RetroClient.getInstance().getRetroService().getRecCoupon(Constants.getInstance().getUSER_KEY());
        call.enqueue(new Callback<ArrayList<Coupon>>() {
            @Override
            public void onResponse(Call<ArrayList<Coupon>> call, Response<ArrayList<Coupon>> response) {
                Log.d("main_activity","연결성공 : " + response.body().get(0).getCoupon_brand());
                coupons_list.addAll(response.body());
                Constants.getInstance().setREC_COUPON(coupons_list.get(0));
                initViewPager();
            }

            @Override
            public void onFailure(Call<ArrayList<Coupon>> call, Throwable t) {
                Log.d("main_activity","연결실패 : " + t.getMessage());
            }
        });
    }


    class PlayingReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("PlayignReceiver", "IN");
            serviceData = intent.getStringExtra("stepService");
            testTxt.setText(serviceData);
            serviceDataTime = intent.getStringExtra("timeService");
            textKm.setText(""+Integer.parseInt(serviceData) * Constants.one_km);
            textKcal.setText(""+Integer.parseInt(serviceData) * Constants.one_kcal);
            testTime.setText(Integer.parseInt(serviceDataTime)/60 + "");

        }
    }




}
package com.example.jinsu.nh_life.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinsu.nh_life.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthActivity extends AppCompatActivity {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.drawer_menu)
    ImageView drawerMenu;
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
    @BindView(R.id.layout_main)
    DrawerLayout layoutMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ButterKnife.bind(this);

        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("통계").setIcon(R.drawable.graph_icon));
        tabLayout.addTab(tabLayout.newTab().setText("랭킹").setIcon(R.drawable.rank_icon_non));
        tabLayout.addTab(tabLayout.newTab().setText("미션").setIcon(R.drawable.mission_icon_non));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue));
        tabLayout.setSelectedTabIndicatorHeight(5);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.graph_icon);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.rank_icon);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.mission_icon);
                        break;
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.graph_icon_non);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.rank_icon_non);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.mission_icon_non);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @OnClick({R.id.drawer_menu, R.id.drawer_btn_coupon, R.id.drawer_btn_point,R.id.nh_logo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.drawer_menu:
                layoutMain.openDrawer(GravityCompat.START);
                break;
            case R.id.drawer_btn_coupon:
                startActivity(new Intent(this, MyCouponListActivity.class));
                break;
            case R.id.drawer_btn_point:
                startActivity(new Intent(this, PointListActivity.class));
                break;
            case R.id.nh_logo:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}

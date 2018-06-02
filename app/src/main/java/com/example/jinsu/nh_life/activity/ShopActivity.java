package com.example.jinsu.nh_life.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.adapter.ShopAdapter;
import com.example.jinsu.nh_life.model.Shop;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopActivity extends AppCompatActivity {

    @BindView(R.id.shop_im_coffee)
    ImageView shopImCoffee;
    @BindView(R.id.shop_layout_coffee)
    LinearLayout shopLayoutCoffee;
    @BindView(R.id.shop_im_bakery)
    ImageView shopImBakery;
    @BindView(R.id.shop_layout_bakery)
    LinearLayout shopLayoutBakery;
    @BindView(R.id.shop_im_desert)
    ImageView shopImDesert;
    @BindView(R.id.shop_layout_desert)
    LinearLayout shopLayoutDesert;
    @BindView(R.id.shop_im_more)
    ImageView shopImMore;
    @BindView(R.id.shop_layout_more)
    LinearLayout shopLayoutMore;
    @BindView(R.id.shop_recycler)
    RecyclerView shopRecycler;
    private ArrayList<Shop> shop_list;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        initRecyclerVIew();
        initListener();

    }

    private void initRecyclerVIew() {
        shop_list = new ArrayList<>();
        mAdapter = new ShopAdapter(this, shop_list);
        shopRecycler.setHasFixedSize(true);
        shopRecycler.setLayoutManager(new GridLayoutManager(this,2));
        shopRecycler.setAdapter(mAdapter);
        setCoffe();

    }

    private void setCoffe() {
        shop_list.clear();
        for(int i=0; i< 15; i++) {
            shop_list.add(new Shop("http://cfile29.uf.tistory.com/image/165587494F72CE8826BCF5", "스타벅스", "아메리카노", "3000p"));
        }
        mAdapter.notifyDataSetChanged();

    }

    private void setBakery() {
        shop_list.clear();
        for(int i=0; i< 15; i++) {
            shop_list.add(new Shop("https://www.paris.co.kr/data/product/aaa.JPG", "파리바게트", "고로케", "1500p"));
        }
        mAdapter.notifyDataSetChanged();

    }

    private void setDesert() {
        shop_list.clear();
        for(int i=0; i< 15; i++) {
            shop_list.add(new Shop("http://www.twosome.co.kr//Twosome_file/PRODUCT/1419_big_img", "투썸플레이스", "레드벨벳 케이크", "7500p"));
        }
        mAdapter.notifyDataSetChanged();

    }


    private void initListener() {
        Glide.with(this).load(R.drawable.coffe).into(shopImCoffee);
        Glide.with(this).load(R.drawable.bakery).into(shopImBakery);
        Glide.with(this).load(R.drawable.desert).into(shopImDesert);
        Glide.with(this).load(R.drawable.burger).into(shopImMore);

        shopLayoutCoffee.setOnClickListener(v ->
        {
            setCoffe();
        });

        shopLayoutBakery.setOnClickListener(v -> {
                setBakery();
        });

        shopLayoutDesert.setOnClickListener(v ->
        {
            setDesert();
        });

        shopLayoutMore.setOnClickListener(v ->
        {

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

package com.example.jinsu.nh_life.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.model.Coupon;
import com.example.jinsu.nh_life.network.RetroClient;

import java.util.ArrayList;

public class MyCouponListAdapter extends RecyclerView.Adapter<MyCouponListAdapter.ViewHolder>{
    private ArrayList<Coupon> list;
    private Context context;
    private onDeleteCallback callback;

    public MyCouponListAdapter(Context context , ArrayList<Coupon> list, onDeleteCallback callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
    }

    public interface onDeleteCallback
    {
        public void onDelete(int position);
    }


    @NonNull
    @Override
    public MyCouponListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon, parent, false);
        MyCouponListAdapter.ViewHolder viewHolder = new MyCouponListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyCouponListAdapter.ViewHolder holder, int position) {


        String start_date = list.get(position).getCoupon_start_date().substring(0,10);
        String expired_date = list.get(position).getCoupon_expired_date().substring(0,10);
        start_date = start_date.replaceAll("-",".");
        expired_date  = expired_date.replaceAll("-",".");
        holder.txt_date.setText(start_date + " ~ " + expired_date);
        holder.txt_content.setText(String.valueOf(list.get(position).getCoupon_content()));
        holder.txt_brand.setText(list.get(position).getCoupon_brand());
        Glide.with(context).load(RetroClient.getInstance().getBASE_URL()+list.get(position).getCoupon_image()).into(holder.im_logo);
        if(!Constants.getInstance().isDELETE_STATUS())
        {
            holder.im_x.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.im_x.setVisibility(View.INVISIBLE);
        }
        holder.im_x.setOnClickListener(v ->
        {
            callback.onDelete(position);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public  static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView im_logo;
        private ImageView im_x;
        public TextView txt_content;
        public TextView txt_brand;
        public TextView txt_date;


        public ViewHolder(View itemView) {
            super(itemView);
            im_x = (ImageView)itemView.findViewById(R.id.my_coupon_im_x);
            im_logo = (ImageView)itemView.findViewById(R.id.my_coupon_im_logo);
            txt_date = (TextView) itemView.findViewById(R.id.my_coupon_txt_date);
            txt_brand = (TextView) itemView.findViewById(R.id.my_coupon_txt_brand);
            txt_content = (TextView) itemView.findViewById(R.id.my_coupon_txt_content);
        }

    }
}

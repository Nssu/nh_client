package com.example.jinsu.nh_life.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.model.Point;

import java.util.ArrayList;

public class PointListAdapter extends RecyclerView.Adapter<PointListAdapter.ViewHolder> {
    private ArrayList<Point> list;
    private Context context;

    public PointListAdapter(Context context,ArrayList<Point> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PointListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point_list, parent, false);
        PointListAdapter.ViewHolder viewHolder = new PointListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PointListAdapter.ViewHolder holder, int position) {

        String date = list.get(position).getPoint_date().substring(0,10);
        holder.txt_date.setText(date);
        holder.txt_price.setText(String.valueOf(list.get(position).getPoint_price()));
        holder.txt_brand.setText(list.get(position).getPoint_brand());
        Log.d("point_list",position + "!!!");
        switch (position % 3)
        {
            case 0:
            {
                Glide.with(context).load(R.drawable.dot_blue).into(holder.im_dot);
                break;
            }
            case 1:
            {
                Glide.with(context).load(R.drawable.dot_green).into(holder.im_dot);
                break;
            }
            case 2:
            {
                Glide.with(context).load(R.drawable.dot_yellow).into(holder.im_dot);
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView im_dot;
        public TextView txt_date;
        public TextView txt_brand;
        public TextView txt_price;


        public ViewHolder(View itemView) {
            super(itemView);
            im_dot = (ImageView)itemView.findViewById(R.id.point_list_im_dot);
            txt_date = (TextView) itemView.findViewById(R.id.point_list_txt_date);
            txt_brand = (TextView) itemView.findViewById(R.id.point_list_txt_brand);
            txt_price = (TextView) itemView.findViewById(R.id.point_list_txt_price);
        }

    }
}

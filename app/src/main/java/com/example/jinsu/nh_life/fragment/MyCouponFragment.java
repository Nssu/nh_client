package com.example.jinsu.nh_life.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.activity.CustomDialogQRCode;


import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class MyCouponFragment extends Fragment {




    RelativeLayout layoutCoupon;
    Unbinder unbinder;
    String coupon;

    public MyCouponFragment(String coupon) {
        this.coupon = coupon;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_viewpager_my_coupon, container, false);
        layoutCoupon = (RelativeLayout)rootView.findViewById(R.id.layout_coupon);
        layoutCoupon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("CLICK", "OnLongClickListener");
                someCallMethod(coupon);
                return true; // 다음 이벤트 계속 진행 false, 이벤트 완료 true
            }
        });
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void someCallMethod(String index) {
        final CustomDialogQRCode qrDialog = new CustomDialogQRCode(getActivity(),index);
        qrDialog.show();
        qrDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
    }
}

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinsu.nh_life.R;
import com.example.jinsu.nh_life.activity.CustomDialogQRCode;
import com.example.jinsu.nh_life.common.Constants;
import com.example.jinsu.nh_life.model.Coupon;
import com.example.jinsu.nh_life.network.RetroClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class MyCouponFragment extends Fragment {


    Unbinder unbinder;
    String coupon;
    @BindView(R.id.main_im_coupon)
    ImageView mainImCoupon;
    @BindView(R.id.bar_txt_content)
    TextView barTxtContent;
    @BindView(R.id.bar_txt_brand)
    TextView barTxtBrand;
    @BindView(R.id.bar_txt_date)
    TextView barTxtDate;
    @BindView(R.id.layout_coupon)
    RelativeLayout layoutCoupon;
    private boolean hint = false;

    private Coupon my_coupon;

    public MyCouponFragment(String coupon) {
        this.coupon = coupon;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_viewpager_my_coupon, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        if(hint) {
            setVIew();
            layoutCoupon.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("CLICK", "OnLongClickListener");
                    someCallMethod(coupon);
                    return true; // 다음 이벤트 계속 진행 false, 이벤트 완료 true
                }
            });
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void someCallMethod(String index) {
        final CustomDialogQRCode qrDialog = new CustomDialogQRCode(getActivity(), index);
        qrDialog.show();
        qrDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
    }

    private void setVIew() {

        Log.d("bar_activity","setVIew()");
        my_coupon = Constants.getInstance().getMY_COUPON();
        if(my_coupon != null) {
            Glide.with(this).load(RetroClient.getInstance().getBASE_URL() + my_coupon.getCoupon_image()).into(mainImCoupon);
            barTxtBrand.setText(my_coupon.getCoupon_brand());
            barTxtContent.setText(my_coupon.getCoupon_content());
            String start_date = my_coupon.getCoupon_start_date().substring(0, 10);
            String expired_date = my_coupon.getCoupon_expired_date().substring(0, 10);
            start_date = start_date.replaceAll("-", ".");
            expired_date = expired_date.replaceAll("-", ".");
            barTxtDate.setText(start_date + " ~ " + expired_date);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        //화면이 실제로 보일 떄
        if(isVisibleToUser)
        {
            hint = true;
            Log.d("bar_activity","실제로 보일 때");
            if(getActivity() != null)
            {
                Log.d("bar_activity","실제로 보일 때 + null아닐 떄");
                setVIew();
            }
        }

    }
}

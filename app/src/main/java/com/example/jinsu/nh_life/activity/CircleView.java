package com.example.jinsu.nh_life.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.jinsu.nh_life.R;

public class CircleView extends View {

    private float angle = 0;
    private Paint paint;
    private RectF rf;

    public CircleView(Context context) {
        super(context);

    }
    public CircleView(Context context, AttributeSet attrs){
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(22);
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE); //원의 윤곽선만 그리는 페인트 스타일
        paint.setAlpha(0x00);


        //부채꼴
        rf = new RectF(9, 15, 516, 516);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.gray2));
        paint.setStrokeWidth(15);
        canvas.drawArc(rf,0, 360, false, paint);
        //sweepAngle : 몇도 그릴지, useCenter : true(부채꼴)
        paint.setStrokeWidth(30);
        paint.setColor(getResources().getColor(R.color.blue));
        canvas.drawArc(rf,270, angle, false, paint);


    }
    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}

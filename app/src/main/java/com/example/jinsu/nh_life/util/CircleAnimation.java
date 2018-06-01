package com.example.jinsu.nh_life.util;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.jinsu.nh_life.activity.CircleView;

public class CircleAnimation extends Animation{
    private CircleView circle;

    private float oldAngle;
    private float newAngle;

    public CircleAnimation(CircleView  circle, int newAngle) {
        this.oldAngle = circle.getAngle();
        this.newAngle = newAngle;
        this.circle = circle;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);

        circle.setAngle(angle);
        circle.requestLayout();
    }
}

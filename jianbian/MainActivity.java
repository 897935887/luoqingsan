package com.youze.demo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScrollView;
    private FrameLayout mNavigation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mNavigation = (FrameLayout) findViewById(R.id.navigation);
        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                System.out.println("i1---" + i + "----i1--" + i1 + "----i2--" + i2 + "------i3--" + i3);
                float f = (float)i1 / 350;
                if (f > 1) {
                    f = 1;
                }
                if (f < 0) {
                    f = 0;
                }
                int intColor = (int) evaluateColor(f, Color.parseColor("#000000"), Color.parseColor("#ff0000"));
                mNavigation.setBackgroundColor(intColor);
            }
        });

    }

    /**
     * 颜色估值器
     *
     * @param fraction   分度值
     * @param startValue 开始颜色
     * @param endValue   结束颜色
     * @return
     */
    public Object evaluateColor(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                (int) ((startB + (int) (fraction * (endB - startB))));
    }
}

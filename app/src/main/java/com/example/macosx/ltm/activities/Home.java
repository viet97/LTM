package com.example.macosx.ltm.activities;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.macosx.ltm.R;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class Home extends AppCompatActivity {

    private static final String TAG = "HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupUI();
    }
    private void setupUI(){
        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("Nav", "Tab", "Strip");
        navigationTabStrip.setTabIndex(0, true);
        navigationTabStrip.setTitleSize(25);
        navigationTabStrip.setStripColor(Color.BLUE);
        navigationTabStrip.setStripWeight(6);
        navigationTabStrip.setStripFactor(2);
        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        navigationTabStrip.setTypeface("fonts/typeface.ttf");
        navigationTabStrip.setCornersRadius(3);
        navigationTabStrip.setAnimationDuration(300);
        navigationTabStrip.setInactiveColor(Color.GRAY);
        navigationTabStrip.setActiveColor(Color.WHITE);
        navigationTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d(TAG, "onPageScrolled: ");
            }

            @Override
            public void onPageSelected(int i) {
                Log.d(TAG, "onPageSelected: ");
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d(TAG, "onPageScrollStateChanged: ");
            }
        });
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.d(TAG, "onStartTabSelected: ");
            }

            @Override
            public void onEndTabSelected(String title, int index) {
                Log.d(TAG, "onEndTabSelected: ");
            }
        });
    }
}

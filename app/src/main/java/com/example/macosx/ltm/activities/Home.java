package com.example.macosx.ltm.activities;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.macosx.ltm.R;
import com.example.macosx.ltm.fragments.tab.BottomNavigationTabType;
import com.example.macosx.ltm.fragments.tab.FriendTab;
import com.example.macosx.ltm.fragments.tab.HomeTab;
import com.example.macosx.ltm.fragments.tab.NotificationTab;


public class Home extends AppCompatActivity {
    BottomNavigationBar bottomTabar;
    FrameLayout contentView;
    private static final String TAG = "HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupUI();
    }
    private void setupUI(){
        contentView = findViewById(R.id.content_view);
        moveTabScreens(BottomNavigationTabType.HOMETAB);

        bottomTabar =  findViewById(R.id.bottom_navigation);
        // Create items
//        AHBottomNavigationItem homeTab = new AHBottomNavigationItem(R.string.home_tab, R.drawable.home, R.color.white);
//        AHBottomNavigationItem notificationTab = new AHBottomNavigationItem(R.string.notification_tab, R.drawable.home, R.color.white);
//        AHBottomNavigationItem friendsTab = new AHBottomNavigationItem(R.string.friend_tab, R.drawable.home, R.color.white);
//
//// Add items
//        bottomTabar.addItem(homeTab);
//        bottomTabar.addItem(notificationTab);
//        bottomTabar.addItem(friendsTab);
//
//// Set background color
////        bottomTabar.setDefaultBackgroundColor(Color.BLUE);
//
//// Disable the translation inside the CoordinatorLayout
//        bottomTabar.setBehaviorTranslationEnabled(false);
//
////// Enable the translation of the FloatingActionButton
////        bottomTabar.manageFloatingActionButtonBehavior(floatingActionButton);
//
//// Change colors
//        bottomTabar.setAccentColor(Color.parseColor("#F63D2B"));
//        bottomTabar.setInactiveColor(Color.parseColor("#747474"));
//
//        bottomTabar.setTranslucentNavigationEnabled(true);
//
//// Manage titles
//        bottomTabar.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//
//// Use colored navigation with circle reveal effect
//        bottomTabar.setColored(true);
//
//        bottomTabar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
//            @Override
//            public boolean onTabSelected(int position, boolean wasSelected) {
//                Log.d(TAG, "onTabSelected: "+ position  + wasSelected);
//                switch (position){
//                    case 0:
//                        moveTabScreens(BottomNavigationTabType.HOMETAB);
//                        break;
//                    case 1:
//                        moveTabScreens(BottomNavigationTabType.NOTIFICATIONTAB);
//                        break;
//                    default:
//                        moveTabScreens(BottomNavigationTabType.FRIENDTAB);
//                        break;
//                }
//                return true;
//            }
//        });
//        bottomTabar.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
//            @Override public void onPositionChange(int y) {
//                Log.d(TAG, "onPositionChange: "+y);
//            }
//        });
        bottomTabar
                .addItem(new BottomNavigationItem(R.drawable.home, "Trang chủ")).setActiveColor(R.color.black).setInActiveColor(R.color.gray)
                .addItem(new BottomNavigationItem(R.drawable.home, "Thông báo")).setActiveColor(R.color.black).setInActiveColor(R.color.gray)
                .addItem(new BottomNavigationItem(R.drawable.home, "Bạn bè")).setActiveColor(R.color.black).setInActiveColor(R.color.gray)
                .initialise();

        bottomTabar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position){
                    case 0:
                        moveTabScreens(BottomNavigationTabType.HOMETAB);
                        break;
                    case 1:
                        moveTabScreens(BottomNavigationTabType.NOTIFICATIONTAB);
                        break;
                    default:
                        moveTabScreens(BottomNavigationTabType.FRIENDTAB);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    private void moveTabScreens(BottomNavigationTabType tabType){
        Fragment fragment;
        switch (tabType){
            case HOMETAB:
                fragment = new HomeTab();
                break;
            case NOTIFICATIONTAB:
                fragment = new NotificationTab();
                break;
            default:
                fragment = new FriendTab();
                break;
        }

        Log.d(TAG, "moveTabScreens: "+fragment);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_view,fragment).commit();
    }
}

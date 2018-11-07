package com.example.macosx.ltm.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.fragments.tab.BottomNavigationTabType;
import com.example.macosx.ltm.fragments.tab.FriendTab;
import com.example.macosx.ltm.fragments.tab.HomeTab;
import com.example.macosx.ltm.fragments.tab.NotificationTab;


public class Home extends Activity {
    BottomNavigationBar bottomTabar;
    FrameLayout contentView;
    BottomNavigationTabType currentTab = BottomNavigationTabType.HOMETAB;
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
                .addItem(new BottomNavigationItem(R.drawable.home, "123123")).setActiveColor(R.color.black).setInActiveColor(R.color.gray)
                .addItem(new BottomNavigationItem(R.drawable.notification, "123")).setActiveColor(R.color.black).setInActiveColor(R.color.gray)
                .addItem(new BottomNavigationItem(R.drawable.friend, "123")).setActiveColor(R.color.black).setInActiveColor(R.color.gray)
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
                currentTab = BottomNavigationTabType.HOMETAB;
                fragment = new HomeTab();
                break;
            case NOTIFICATIONTAB:
                currentTab = BottomNavigationTabType.NOTIFICATIONTAB;
                fragment = new NotificationTab();
                break;
            default:
                currentTab = BottomNavigationTabType.FRIENDTAB;
                fragment = new FriendTab();
                break;
        }

        Log.d(TAG, "moveTabScreens: "+fragment);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_view,fragment).commit();
    }

    @Override
    public void onBackPressed() {

        if (currentTab != BottomNavigationTabType.HOMETAB){
            bottomTabar.selectTab(0);
        }else {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.logout_dialog_title))
                    .setMessage(getString(R.string.logout_dialog_message))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Home.super.onBackPressed();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        }
    }

}

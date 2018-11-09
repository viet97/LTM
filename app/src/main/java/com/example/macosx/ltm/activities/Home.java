package com.example.macosx.ltm.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class Home extends Activity {
    public BottomNavigationBar bottomTabar;
    FrameLayout contentView;
    BottomNavigationTabType currentTab = BottomNavigationTabType.HOMETAB;
    private static final String TAG = "HOME";
    public static Home instance;
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
    protected void onResume() {
        super.onResume();
        instance = this;
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

    public void moveToDetailPost(int id,String name,String time,String content,int like,int comment){
        Intent intent = new Intent(this,DetailPostActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        intent.putExtra("content",content);
        intent.putExtra("time",time);
        intent.putExtra("like",like);
        intent.putExtra("comment",comment);
        startActivity(intent);
    }

}

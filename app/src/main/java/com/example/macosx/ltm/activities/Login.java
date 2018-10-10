package com.example.macosx.ltm.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.macosx.ltm.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private CircularProgressButton loginButton;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.login_button:
                loginButton.startAnimation();
                final Handler handle1 = new Handler();
                handle1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loginButton.doneLoadingAnimation(R.color.tranparency,BitmapFactory.decodeResource(getResources(),R.drawable.login_success));
                        Handler handle2 = new Handler();
                        handle2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(Login.this,Home.class));
                                Handler handle3 = new Handler();
                                handle3.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loginButton.revertAnimation();
                                    }
                                },1000);
                            }
                        },1000);

                    }
                },2000);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginButton.dispose();
    }
}

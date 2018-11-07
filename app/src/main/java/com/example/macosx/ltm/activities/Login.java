package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.LoginService;
import com.example.macosx.ltm.network.response.LoginResponse;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Activity implements View.OnClickListener {

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
                if (username.getText().toString().trim().equals("")|| username.getText().toString().trim().equals("")){
                    com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(Login.this,getString(R.string.error),getString(R.string.validate_login_message));
                }else{
                    loginButton.startAnimation();
                    final Handler handle1 = new Handler();

                    LoginService loginService = NetworkManager.getInstance().create(LoginService.class);
                    loginService.login(username.getText().toString(),password.getText().toString()).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse.getErrorCode().equals("0")){
                                DbContext.getInstance().setCurrentUser(loginResponse.getUser());
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

                            }else{

                                com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(Login.this,getString(R.string.error),loginResponse.getMsg());
                                loginButton.revertAnimation();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                            com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(Login.this,getString(R.string.error),getString(R.string.failur_message));
                            loginButton.revertAnimation();
                        }
                    });

                }


                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginButton.dispose();
    }
}

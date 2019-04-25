package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.SocketAsyncTask;
import com.example.macosx.ltm.network.api.LoginService;
import com.example.macosx.ltm.network.response.LoginResponse;
import com.example.macosx.ltm.ultils.Constant;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.socketio.Acknowledge;
import com.koushikdutta.async.http.socketio.ConnectCallback;
import com.koushikdutta.async.http.socketio.EventCallback;
import com.koushikdutta.async.http.socketio.JSONCallback;
import com.koushikdutta.async.http.socketio.SocketIOClient;
import com.koushikdutta.async.http.socketio.StringCallback;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.gusavila92.websocketclient.WebSocketClient;

public class Login extends Activity implements View.OnClickListener {
    public static Login instance = null;
    private CircularProgressButton loginButton;
    public EditText username;
    public EditText password;
    public TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI();
        checkLogin();
    }

    private void checkLogin(){
        String username = getSharedPreferences("author",MODE_PRIVATE).getString("username","");
        String password = getSharedPreferences("author",MODE_PRIVATE).getString("password","");
        if (!username.equals("") && !password.equals("")){
            this.username.setText(username);
            this.password.setText(password);
            this.loginButton.performClick();
        }
    }

    private void setupUI(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        register = findViewById(R.id.register);
        loginButton.setOnClickListener(this);
        register.setOnClickListener(this);
//        loginButton.callOnClick();





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
                                SharedPreferences.Editor editor=  getSharedPreferences("author",MODE_PRIVATE).edit();
                                editor.putString("username",username.getText().toString());
                                editor.putString("password",password.getText().toString());

                                editor.apply();
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
                            Log.d("Login", "onFailure: "+throwable.toString());
                        }
                    });

                }


                break;
            case R.id.register:
                startActivity(new Intent(this,Register.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginButton.dispose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        instance= this;
    }
}

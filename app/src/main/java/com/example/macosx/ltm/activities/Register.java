package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.LoginService;
import com.example.macosx.ltm.network.api.RegisterService;
import com.example.macosx.ltm.network.response.LoginResponse;
import com.example.macosx.ltm.network.response.VoidResponse;

import org.w3c.dom.Text;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Activity implements View.OnClickListener {
    private CircularProgressButton registerButton;
    private EditText username;
    private EditText password;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUI();
    }

    private void setupUI(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        registerButton = findViewById(R.id.regis_button);
        registerButton.setOnClickListener(this);
//        loginButton.callOnClick();


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.regis_button:
                if (username.getText().toString().trim().equals("")|| username.getText().toString().trim().equals("")|| name.getText().toString().trim().equals("")){
                    com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(Register.this,getString(R.string.error),"Thông tin đăng ký không được để trống");
                }else{
                registerButton.startAnimation();
                final Handler handle1 = new Handler();

                RegisterService registerService = NetworkManager.getInstance().create(RegisterService.class);
                    registerService.register(username.getText().toString(),password.getText().toString(),name.getText().toString()).enqueue(new Callback<VoidResponse>() {
                    @Override
                    public void onResponse(Call<VoidResponse> call, Response<VoidResponse> response) {
                        VoidResponse voidResponse = response.body();
                        if (voidResponse.getErrorCode().equals("0")){
                            handle1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    registerButton.doneLoadingAnimation(R.color.tranparency,BitmapFactory.decodeResource(getResources(),R.drawable.login_success));
                                    Handler handle2 = new Handler();
                                    handle2.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Login.instance.username.setText(username.getText());
                                            Login.instance.password.setText(password.getText());
                                            onBackPressed();
                                            Handler handle3 = new Handler();
                                            handle3.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    registerButton.revertAnimation();
                                                }
                                            },1000);
                                        }
                                    },1000);

                                }
                            },2000);

                        }else{

                            com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(Register.this,getString(R.string.error),voidResponse.getMsg());
                            registerButton.revertAnimation();
                        }

                    }

                    @Override
                    public void onFailure(Call<VoidResponse> call, Throwable throwable) {
                        com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(Register.this,getString(R.string.error),getString(R.string.failur_message));
                        registerButton.revertAnimation();
                        Log.d("Login", "onFailure: "+throwable.toString());
                    }
                });

                }


                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerButton.dispose();
    }
}

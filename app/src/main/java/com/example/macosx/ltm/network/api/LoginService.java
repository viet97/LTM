package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("username") String username,@Field("password") String password);
}

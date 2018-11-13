package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.LoginResponse;
import com.example.macosx.ltm.network.response.VoidResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LogoutService {
    @FormUrlEncoded
    @POST("logout")
    Call<VoidResponse> login(@Field("username") String username, @Field("password") String password);
}

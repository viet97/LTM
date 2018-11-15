package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.LoginResponse;
import com.example.macosx.ltm.network.response.VoidResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterService {
    @FormUrlEncoded
    @POST("register")
    Call<VoidResponse> register(@Field("username") String username, @Field("password") String password, @Field("name") String name);
}

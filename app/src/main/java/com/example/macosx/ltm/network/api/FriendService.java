package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.FriendResponse;
import com.example.macosx.ltm.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FriendService {
    @GET
    Call<FriendResponse> getAllFriends(@Url String url);
}

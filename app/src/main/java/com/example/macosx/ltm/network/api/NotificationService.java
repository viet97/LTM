package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NotificationService {
    @GET
    Call<NotificationResponse> getAllNotifications(@Url String url);
}

package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.ChatResponse;
import com.example.macosx.ltm.network.response.CommentResponse;
import com.example.macosx.ltm.network.response.VoidResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ChatService {
    @GET
    Call<ChatResponse> getAllMessages(@Url String url);

    @FormUrlEncoded
    @POST("sendMessage")
    Call<VoidResponse> sendMessage(@Field("id_receive") int id_receive, @Field("id_send") int id_send, @Field("content") String content, @Field("time") String time);
}

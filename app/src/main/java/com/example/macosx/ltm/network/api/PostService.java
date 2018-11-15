package com.example.macosx.ltm.network.api;


import com.example.macosx.ltm.network.response.LoginResponse;
import com.example.macosx.ltm.network.response.PostResponse;
import com.example.macosx.ltm.network.response.VoidResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface PostService {
    @GET
    Call<PostResponse> getAllPosts(@Url String url);
    @FormUrlEncoded
    @POST("post")
    Call<VoidResponse> post(@Field("user_id_send") int user_id_send, @Field("user_id_receive") int user_id_receive, @Field("content") String content);

    @DELETE
    Call<VoidResponse> delete(@Url String url);

}

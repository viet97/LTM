package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.LikeCountResponse;
import com.example.macosx.ltm.network.response.VoidResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LikeService {
    @FormUrlEncoded
    @POST("like")
    Call<VoidResponse> like(@Field("user_like_id") int user_like_id, @Field("post_id") int post_id);
    @GET
    Call<LikeCountResponse> countLike(@Url String url);
}

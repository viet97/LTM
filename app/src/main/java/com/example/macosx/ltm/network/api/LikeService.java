package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.VoidResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LikeService {
    @FormUrlEncoded
    @POST("like")
    Call<VoidResponse> like(@Field("user_like_id") int user_like_id, @Field("post_id") int post_id);

}

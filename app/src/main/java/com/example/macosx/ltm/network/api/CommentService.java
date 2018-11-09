package com.example.macosx.ltm.network.api;

import com.example.macosx.ltm.network.response.CommentResponse;
import com.example.macosx.ltm.network.response.FriendResponse;
import com.example.macosx.ltm.network.response.VoidResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface CommentService {
    @GET
    Call<CommentResponse> getAllComment(@Url String url);
    @FormUrlEncoded
    @POST("comment")
    Call<VoidResponse> comment(@Field("user_comment_id") int user_comment_id, @Field("post_id") int post_id,@Field("content") String content);
}

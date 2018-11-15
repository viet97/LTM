package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.macosx.ltm.R;
import com.example.macosx.ltm.adapters.ListPostAdapter;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.PostService;
import com.example.macosx.ltm.network.response.PostResponse;
import com.example.macosx.ltm.ultils.Dialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendWallActivity extends Activity {
        public int id = -1;
        private static final String TAG = "HomeTab";
        public RecyclerView listPost;
        private ArrayList<Post> listPostData;
        EditText statusPost;
        TextView pickImage;
        public static FriendWallActivity instance ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_wall);
        setupUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    private void setupUI() {
        listPostData = new ArrayList<>();

        listPost = findViewById(R.id.list_post);
        listPost.setAdapter(new ListPostAdapter());
        listPost.setLayoutManager(new LinearLayoutManager(this));

        statusPost = findViewById(R.id.status_post);
        statusPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FriendWallActivity.this,PostStatus.class));
            }
        });
        getAllPost();

    }

    @Override
    protected void onResume() {
        super.onResume();
        instance= this;
    }

    public void getAllPost( ) {
        id = Integer.parseInt(getIntent().getExtras().get("id").toString());
        PostService postService = NetworkManager.getInstance().create(PostService.class);
        String url = "receive_posts?id="+id+"&user_request_id="+DbContext.getInstance().getCurrentUser().getId();
        postService.getAllPosts(url).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse postResponse = response.body();
                if (postResponse.getErrorCode().equals("0")) {
                    DbContext.getInstance().setListPosts(postResponse.getPosts());
                    DbContext.getInstance().setListIsLikes(postResponse.getIsLike());
                    listPost.getAdapter().notifyDataSetChanged();

                }else{
                    Dialog.instance.showMessageDialog(FriendWallActivity.this,getString(R.string.error),postResponse.getMsg());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Dialog.instance.showMessageDialog(FriendWallActivity.this,getString(R.string.error),getString(R.string.failur_message));

            }

        });
    }
    public void moveToDetailPost(int id,String name,String time,String content,int like,int comment,int islike){
        Intent intent = new Intent(this,DetailPostActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        intent.putExtra("content",content);
        intent.putExtra("time",time);
        intent.putExtra("like",like);
        intent.putExtra("comment",comment);
        intent.putExtra("islike",islike);
        startActivity(intent);
    }
}

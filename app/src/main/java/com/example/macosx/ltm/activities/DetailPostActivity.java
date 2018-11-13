package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.adapters.ListCommentAdapter;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Comment;
import com.example.macosx.ltm.database.models.User;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.CommentService;
import com.example.macosx.ltm.network.api.FriendService;
import com.example.macosx.ltm.network.api.LikeService;
import com.example.macosx.ltm.network.response.CommentCountResponse;
import com.example.macosx.ltm.network.response.CommentResponse;
import com.example.macosx.ltm.network.response.FriendResponse;
import com.example.macosx.ltm.network.response.LikeCountResponse;
import com.example.macosx.ltm.network.response.VoidResponse;
import com.example.macosx.ltm.ultils.Dialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPostActivity extends Activity {
    private ImageView avatar;
    private TextView name;
    private TextView content;
    private TextView time;
    private TextView like;
    private TextView comment;
    public RecyclerView rv_list_comments;
    private EditText input_content;
    private Button send;
    private int likeCount = 0;
    private int commentCount =0;
    public static DetailPostActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        time = findViewById(R.id.time);
        rv_list_comments = findViewById(R.id.rv_list_comment);
        input_content = findViewById(R.id.content_input);
        send = findViewById(R.id.send);
        like = findViewById(R.id.like);
        content = findViewById(R.id.content);
        comment = findViewById(R.id.comment);

        if (getIntent().getExtras().get("name")!= null) name.setText(getIntent().getExtras().get("name").toString());
        if (getIntent().getExtras().get("time")!= null) time.setText(getIntent().getExtras().get("time").toString());
        if (getIntent().getExtras().get("content")!= null) content.setText(getIntent().getExtras().get("content").toString());
        if (getIntent().getExtras().get("like")!= null) {
            likeCount = Integer.parseInt(getIntent().getExtras().get("like").toString());
            like.setText(getIntent().getExtras().get("like").toString()+ " likes");
        }
        if (getIntent().getExtras().get("comment")!= null) {
            commentCount = Integer.parseInt(getIntent().getExtras().get("comment").toString());
            comment.setText(getIntent().getExtras().get("comment").toString()+ " comments") ;
        }
        rv_list_comments.setAdapter(new ListCommentAdapter());
        rv_list_comments.setHasFixedSize(true);
        rv_list_comments.setLayoutManager(new LinearLayoutManager(this));
        getListComments(this);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_content.getText().toString().trim().equals("")){
                    postComment();
                }
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LikeService likeService = NetworkManager.getInstance().create(LikeService.class);
                likeService.like(DbContext.getInstance().getCurrentUser().getId(),Integer.parseInt(getIntent().getExtras().get("id").toString())).enqueue(new Callback<VoidResponse>() {
                    @Override
                    public void onResponse(Call<VoidResponse> call, Response<VoidResponse> response) {
                        VoidResponse voidResponse = response.body();
                        if (voidResponse.getErrorCode().equals("0")) {
                            DetailPostActivity.instance.updateLike();
                        } else {
                            Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), voidResponse.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<VoidResponse> call, Throwable t) {
                        Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), getString(R.string.failur_message));
                    }
                });
            }
        });


    }
    private void postComment(){
        CommentService commentService = NetworkManager.getInstance().create(CommentService.class);
        commentService.comment(DbContext.getInstance().getCurrentUser().getId(),Integer.parseInt(getIntent().getExtras().get("id").toString()),input_content.getText().toString()).enqueue(new Callback<VoidResponse>() {
            @Override
            public void onResponse(Call<VoidResponse> call, Response<VoidResponse> response) {
                VoidResponse voidResponse = response.body();
                if (voidResponse.getErrorCode().equals("0")) {
                    getListComments(DetailPostActivity.this);
                    input_content.setText("");
                    updateComment();
                } else {
                    Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), voidResponse.getMsg());
                }
            }

            @Override
            public void onFailure(Call<VoidResponse> call, Throwable t) {
                Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), getString(R.string.failur_message));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        instance = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    private void getListComments(final Context context) {
            CommentService commentService = NetworkManager.getInstance().create(CommentService.class);
            String url = "show_comments?id=" + getIntent().getExtras().get("id").toString();
        commentService.getAllComment(url).enqueue(new Callback<CommentResponse>() {
                @Override
                public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                    CommentResponse commentResponse = response.body();
                    if (commentResponse.getErrorCode().equals("0")) {
                        DbContext.getInstance().setListComments(commentResponse.getComments());
                        rv_list_comments.getAdapter().notifyDataSetChanged();
                    } else {
                        Dialog.instance.showMessageDialog(context, context.getString(R.string.error), commentResponse.getMsg());
                    }
                }

                @Override
                public void onFailure(Call<CommentResponse> call, Throwable throwable) {
                    Dialog.instance.showMessageDialog(context, context.getString(R.string.error), context.getString(R.string.failur_message));

                }
            });
    }

    public void updateLike(){
        LikeService likeService = NetworkManager.getInstance().create(LikeService.class);
        String url = "count_like?id="+getIntent().getExtras().get("id");
        likeService.countLike(url).enqueue(new Callback<LikeCountResponse>() {
            @Override
            public void onResponse(Call<LikeCountResponse> call, Response<LikeCountResponse> response) {
                LikeCountResponse likeCountResponse = response.body();
                if (likeCountResponse.getErrorCode().equals("0")){
                    like.setText(likeCountResponse.getLike_count()+" thích");
                }else{
                    Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), likeCountResponse.getMsg());
                }
            }

            @Override
            public void onFailure(Call<LikeCountResponse> call, Throwable t) {
                Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), getString(R.string.failur_message));
            }
        });
    }

    public void  updateComment(){
        CommentService commentService = NetworkManager.getInstance().create(CommentService.class);
        String url = "count_comment?id="+getIntent().getExtras().get("id");
        commentService.countComment(url).enqueue(new Callback<CommentCountResponse>() {
            @Override
            public void onResponse(Call<CommentCountResponse> call, Response<CommentCountResponse> response) {
                CommentCountResponse commentCountResponse = response.body();
                if (commentCountResponse.getErrorCode().equals("0")){
                    comment.setText(commentCountResponse.getComment_count()+" comment");
                }else{
                    Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), commentCountResponse.getMsg());
                }
            }

            @Override
            public void onFailure(Call<CommentCountResponse> call, Throwable t) {
                Dialog.instance.showMessageDialog(DetailPostActivity.this, getString(R.string.error), getString(R.string.failur_message));
            }
        });
    }
}

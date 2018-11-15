package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.fragments.tab.HomeTab;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.PostService;
import com.example.macosx.ltm.network.response.VoidResponse;
import com.example.macosx.ltm.ultils.Dialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostStatus extends Activity {

    private static final int PICK_IMAGE = 1;
    private TextView pickImage;
    private ImageView back;
    private TextView post;
    private EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_status);
        setupUI();
    }

    private void setupUI() {

//        pickImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
//            }
//        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        post = findViewById(R.id.post);
        content = findViewById(R.id.content);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content.getText().toString().equals("")){
                    Dialog.instance.showMessageDialog(PostStatus.this,getString(R.string.error),"Nội dung không được để trống");

                }else{
                    if (FriendWallActivity.instance != null) {
                        PostService postService = NetworkManager.getInstance().create(PostService.class);
                        postService.post(DbContext.getInstance().getCurrentUser().getId(),FriendWallActivity.instance.id,content.getText().toString()).enqueue(new Callback<VoidResponse>() {
                            @Override
                            public void onResponse(Call<VoidResponse> call, Response<VoidResponse> response) {
                                VoidResponse voidResponse = response.body();
                                if (voidResponse.getErrorCode().equals("0")) {
                                    FriendWallActivity.instance.getAllPost();
                                    onBackPressed();
                                }else{
                                    Dialog.instance.showMessageDialog(PostStatus.this,getString(R.string.error),voidResponse.getMsg());
                                }
                            }

                            @Override
                            public void onFailure(Call<VoidResponse> call, Throwable t) {
                                Dialog.instance.showMessageDialog(PostStatus.this,getString(R.string.error),getString(R.string.failur_message));

                            }
                        });
                    }else{
                        PostService postService = NetworkManager.getInstance().create(PostService.class);

                        postService.post(DbContext.getInstance().getCurrentUser().getId(),DbContext.getInstance().getCurrentUser().getId(),content.getText().toString()).enqueue(new Callback<VoidResponse>() {
                            @Override
                            public void onResponse(Call<VoidResponse> call, Response<VoidResponse> response) {
                                VoidResponse voidResponse = response.body();
                                if (voidResponse.getErrorCode().equals("0")) {
                                    HomeTab.instance.getAllPost(Home.instance,DbContext.getInstance().getCurrentUser().getId());
                                    onBackPressed();
                                }else{
                                    Dialog.instance.showMessageDialog(PostStatus.this,getString(R.string.error),voidResponse.getMsg());
                                }
                            }

                            @Override
                            public void onFailure(Call<VoidResponse> call, Throwable t) {
                                Dialog.instance.showMessageDialog(PostStatus.this,getString(R.string.error),getString(R.string.failur_message));

                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

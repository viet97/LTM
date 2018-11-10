package com.example.macosx.ltm.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Network;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.activities.FriendWallActivity;
import com.example.macosx.ltm.activities.Home;
import com.example.macosx.ltm.activities.Login;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.fragments.tab.HomeTab;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.PostService;
import com.example.macosx.ltm.network.response.VoidResponse;
import com.example.macosx.ltm.ultils.Dialog;
import com.example.macosx.ltm.ultils.Ultils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPostAdapter extends RecyclerView.Adapter<ListPostAdapter.ListPostViewHolder> {
    public class ListPostViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView name;
        private TextView time;
        private TextView content;
        private ImageButton like;
        private ImageButton comment;
        private TextView likeCount;
        private TextView commentCount;
        private RelativeLayout container;
        public ListPostViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar =itemView.findViewById(R.id.avatar);
            name =itemView.findViewById(R.id.name);
            time =itemView.findViewById(R.id.time);
            content =itemView.findViewById(R.id.content);
            like =itemView.findViewById(R.id.like);
            comment =itemView.findViewById(R.id.comment);
            likeCount =itemView.findViewById(R.id.like_count);
            commentCount =itemView.findViewById(R.id.comment_count);
            container =  itemView.findViewById(R.id.container);
        }
    }

    @NonNull
    @Override
    public ListPostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_post_item, viewGroup, false);

        ListPostViewHolder vh = new ListPostViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListPostViewHolder listPostViewHolder, int i) {
        final Post post = DbContext.getInstance().getListPosts().get(i);
        String namesend =Ultils.instance.getNameOfPost(post.getUser_id_send());
        String namereceive =Ultils.instance.getNameOfPost(post.getUser_id_receive());
        String name = "";
        if (namesend.equals(namereceive)){
            name = namesend;
        }else{
            name = namesend+ ">" + namereceive;
        }
        listPostViewHolder.name.setText(name);
        listPostViewHolder.time.setText(post.getCreate_time());
        listPostViewHolder.content.setText(post.getContent());
        listPostViewHolder.likeCount.setText(String.valueOf( post.getLike_count()));
        listPostViewHolder.commentCount.setText(String.valueOf( post.getComment_count()));
        final String finalName = name;
        listPostViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FriendWallActivity.instance!= null){
                    FriendWallActivity.instance.moveToDetailPost(post.getId(), finalName,post.getCreate_time(),post.getContent(),post.getLike_count(),post.getComment_count());
                }else{
                    Home.instance.moveToDetailPost(post.getId(), finalName,post.getCreate_time(),post.getContent(),post.getLike_count(),post.getComment_count());
                }
            }
        });
    Boolean canDelete = false;
    if (post.getUser_id_receive() == DbContext.getInstance().getCurrentUser().getId() || post.getUser_id_send() == DbContext.getInstance().getCurrentUser().getId()){
        canDelete = true;
    }
    if (canDelete) {
        listPostViewHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Context context;
                if (FriendWallActivity.instance != null) {
                    context = FriendWallActivity.instance;
                } else {
                    context = Home.instance;
                }
                new AlertDialog.Builder(context)
                        .setTitle("Xóa")
                        .setMessage("Bạn muốn xóa bài đăng này?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                PostService postService = NetworkManager.getInstance().create(PostService.class);
                                postService.delete("delete_post?id=" + post.getId()).enqueue(new Callback<VoidResponse>() {
                                    @Override
                                    public void onResponse(Call<VoidResponse> call, Response<VoidResponse> response) {
                                        VoidResponse voidResponse = response.body();
                                        if (voidResponse.getErrorCode().equals("0")) {
                                            if (FriendWallActivity.instance != null) {
                                                FriendWallActivity.instance.getAllPost();
                                            } else {
                                                HomeTab.instance.getAllPost(context, DbContext.getInstance().getCurrentUser().getId());
                                            }
                                        } else {
                                            com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(context, context.getString(R.string.error), voidResponse.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<VoidResponse> call, Throwable t) {
                                        com.example.macosx.ltm.ultils.Dialog.instance.showMessageDialog(context, context.getString(R.string.error), context.getString(R.string.failur_message));
                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        });
    }
    }

    @Override
    public int getItemCount() {
        return DbContext.getInstance().getListPosts().size();
    }




}

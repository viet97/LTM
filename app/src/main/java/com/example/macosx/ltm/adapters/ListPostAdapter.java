package com.example.macosx.ltm.adapters;

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
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.ultils.Ultils;

import java.util.ArrayList;

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
        listPostViewHolder.likeCount.setText(String.valueOf( post.getLike()));
        listPostViewHolder.commentCount.setText(String.valueOf( post.getComment()));
        final String finalName = name;
        listPostViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FriendWallActivity.instance!= null){
                    FriendWallActivity.instance.moveToDetailPost(post.getId(), finalName,post.getCreate_time(),post.getContent(),post.getLike(),post.getComment());
                }else{
                    Home.instance.moveToDetailPost(post.getId(), finalName,post.getCreate_time(),post.getContent(),post.getLike(),post.getComment());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return DbContext.getInstance().getListPosts().size();
    }




}

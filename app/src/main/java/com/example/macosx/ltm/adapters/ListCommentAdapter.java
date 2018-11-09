package com.example.macosx.ltm.adapters;


import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Comment;
import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.database.models.User;
import com.example.macosx.ltm.fragments.tab.FriendTab;
import com.example.macosx.ltm.ultils.Ultils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListCommentAdapter extends RecyclerView.Adapter<ListCommentAdapter.ListCommentViewHolder> {
    public class ListCommentViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView name;
        private TextView time;
        private TextView content;
        private RelativeLayout container;

        public ListCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
        }
    }

    @NonNull
    @Override
    public ListCommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_comment_item,viewGroup,false);
        return new ListCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCommentViewHolder listFriendViewHolder, int i) {
        Comment comment = DbContext.getInstance().getListComments().get(i);
        listFriendViewHolder.content.setText(comment.getContent());
        listFriendViewHolder.time.setText(comment.getCreate_time());
        listFriendViewHolder.name.setText(Ultils.instance.getNameOfPost(comment.getUser_comment_id()));


    }

    @Override
    public int getItemCount() {
        return DbContext.getInstance().getListComments().size();
    }


}

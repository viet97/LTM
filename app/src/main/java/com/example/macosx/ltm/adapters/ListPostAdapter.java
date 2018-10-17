package com.example.macosx.ltm.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.models.Post;

import java.util.ArrayList;

public class ListPostAdapter extends RecyclerView.Adapter<ListPostAdapter.ListPostViewHolder> {
    private ArrayList<Post> listPost;
    public class ListPostViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView name;
        private TextView time;
        private TextView content;
        private ImageButton like;
        private ImageButton comment;
        private TextView likeCount;
        private TextView commentCount;
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
        }
    }
    public ListPostAdapter(ArrayList<Post> listPost){
        this.listPost = listPost;
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
        listPostViewHolder.name.setText(listPost.get(i).getName());
        listPostViewHolder.time.setText(listPost.get(i).getTime());
        listPostViewHolder.content.setText(listPost.get(i).getContent());
        listPostViewHolder.likeCount.setText(String.valueOf( listPost.get(i).getLike()));
        listPostViewHolder.commentCount.setText(String.valueOf( listPost.get(i).getComment()));
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }




}

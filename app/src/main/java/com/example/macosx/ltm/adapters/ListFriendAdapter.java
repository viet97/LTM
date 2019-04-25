package com.example.macosx.ltm.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.database.models.User;
import com.example.macosx.ltm.fragments.tab.FriendTab;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListFriendAdapter extends RecyclerView.Adapter<ListFriendAdapter.ListFriendViewHolder> {
    public class ListFriendViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private ImageView online;
        private TextView name;
        private RelativeLayout container;
        private ImageView message;

        public ListFriendViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            online = itemView.findViewById(R.id.online);
            name = itemView.findViewById(R.id.name);
            container = itemView.findViewById(R.id.container);
            message = itemView.findViewById(R.id.message);
        }
    }

    @NonNull
    @Override
    public ListFriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_friend_item, viewGroup, false);
        return new ListFriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFriendViewHolder listFriendViewHolder, int i) {
        final User friend = DbContext.getInstance().getListFriends().get(i);
        listFriendViewHolder.name.setText(friend.getName());
        if (friend.getStatus() == 1) {
            listFriendViewHolder.online.setVisibility(View.VISIBLE);
        } else {
            listFriendViewHolder.online.setVisibility(View.INVISIBLE);
        }
        listFriendViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendTab.instance.moveToFriendsWall(friend.getId());
            }
        });
        listFriendViewHolder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendTab.instance.moveToChat(DbContext.getInstance().getCurrentUser().getId(), friend.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return DbContext.getInstance().getListFriends().size();
    }


}

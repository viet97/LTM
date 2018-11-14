package com.example.macosx.ltm.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Notification;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListNotiAdapter extends RecyclerView.Adapter<ListNotiAdapter.ListNotiViewHolder> {

    public class ListNotiViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView content;
        private TextView time;
        public ListNotiViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
        }
    }
    @NonNull
    @Override
    public ListNotiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_noti_item,viewGroup,false);
        return new ListNotiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNotiViewHolder listNotiViewHolder, int i) {
        listNotiViewHolder.content.setText(DbContext.getInstance().getListNotifications().get(i).getContent());
        listNotiViewHolder.time.setText(DbContext.getInstance().getListNotifications().get(i).getCreate_time());
    }

    @Override
    public int getItemCount() {
        return DbContext.getInstance().getListNotifications().size();
    }


}

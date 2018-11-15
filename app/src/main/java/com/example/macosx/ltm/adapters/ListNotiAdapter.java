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
import com.example.macosx.ltm.activities.FriendWallActivity;
import com.example.macosx.ltm.activities.Home;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.ultils.Ultils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListNotiAdapter extends RecyclerView.Adapter<ListNotiAdapter.ListNotiViewHolder> {

    public class ListNotiViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView content;
        private TextView time;
        private RelativeLayout container;
        public ListNotiViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            container = itemView.findViewById(R.id.container);

        }
    }
    @NonNull
    @Override
    public ListNotiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_noti_item,viewGroup,false);
        return new ListNotiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNotiViewHolder listNotiViewHolder, final int i) {
        listNotiViewHolder.content.setText(DbContext.getInstance().getListNotifications().get(i).getContent());
        listNotiViewHolder.time.setText(DbContext.getInstance().getListNotifications().get(i).getCreate_time());
        listNotiViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = DbContext.getInstance().getListNotiPosts().get(i);
                int isLike = DbContext.getInstance().getListNotiIsLike().get(i);
                String name = "";
                String namesend =Ultils.instance.getNameOfPost(post.getUser_id_send());
                String namereceive =Ultils.instance.getNameOfPost(post.getUser_id_receive());
                if (namesend.equals(namereceive)){
                    name = namesend;
                }else{
                    name = namesend+ ">" + namereceive;
                }
                if (FriendWallActivity.instance!= null){
                    FriendWallActivity.instance.moveToDetailPost(post.getId(), name,post.getCreate_time(),post.getContent(),post.getLike_count(),post.getComment_count(),isLike);
                }else{
                    Home.instance.moveToDetailPost(post.getId(), name,post.getCreate_time(),post.getContent(),post.getLike_count(),post.getComment_count(),isLike);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return DbContext.getInstance().getListNotifications().size();
    }


}

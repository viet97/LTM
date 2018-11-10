package com.example.macosx.ltm.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.macosx.ltm.activities.DetailPostActivity;
import com.example.macosx.ltm.activities.FriendWallActivity;
import com.example.macosx.ltm.activities.Home;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Comment;
import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.database.models.User;
import com.example.macosx.ltm.fragments.tab.FriendTab;
import com.example.macosx.ltm.fragments.tab.HomeTab;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.PostService;
import com.example.macosx.ltm.network.response.VoidResponse;
import com.example.macosx.ltm.ultils.Ultils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(@NonNull ListCommentViewHolder listCommentViewHolder, int i) {
        Comment comment = DbContext.getInstance().getListComments().get(i);
        listCommentViewHolder.content.setText(comment.getContent());
        listCommentViewHolder.time.setText(comment.getCreate_time());
        listCommentViewHolder.name.setText(Ultils.instance.getNameOfPost(comment.getUser_comment_id()));
        Boolean canDelete = false;
        if (comment.getUser_comment_id() == DbContext.getInstance().getCurrentUser().getId()){
            canDelete = true;
        }
        if (canDelete) {
            listCommentViewHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Context context = DetailPostActivity.instance;

                    new AlertDialog.Builder(context)
                            .setTitle("Xóa")
                            .setMessage("Bạn muốn xóa bình luận này?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    PostService postService = NetworkManager.getInstance().create(PostService.class);
                                    postService.delete("delete_post?id=" + DetailPostActivity.instance.getIntent().getExtras().get("id").toString()).enqueue(new Callback<VoidResponse>() {
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
        return DbContext.getInstance().getListComments().size();
    }


}

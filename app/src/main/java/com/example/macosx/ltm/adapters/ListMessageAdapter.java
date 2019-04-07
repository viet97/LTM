package com.example.macosx.ltm.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.macosx.ltm.activities.DetailPostActivity;
import com.example.macosx.ltm.activities.FriendWallActivity;
import com.example.macosx.ltm.activities.Home;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Comment;
import com.example.macosx.ltm.database.models.Message;
import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.database.models.User;
import com.example.macosx.ltm.fragments.tab.FriendTab;
import com.example.macosx.ltm.fragments.tab.HomeTab;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.CommentService;
import com.example.macosx.ltm.network.api.PostService;
import com.example.macosx.ltm.network.response.VoidResponse;
import com.example.macosx.ltm.ultils.Dialog;
import com.example.macosx.ltm.ultils.Ultils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMessageAdapter extends RecyclerView.Adapter<ListMessageAdapter.ListMessageViewHolder> {
    private static final String TAG = "LISTMESSAGEADAPTER";

    public class ListMessageViewHolder extends RecyclerView.ViewHolder{
    TextView timeSend;
    TextView timeReceive;
    TextView contentSend;
    TextView contentReceive;
    RelativeLayout sendLayout;
    RelativeLayout receiveLayout;
        public ListMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            timeReceive = itemView.findViewById(R.id.time_receive);
            timeSend = itemView.findViewById(R.id.time_send);
            contentSend = itemView.findViewById(R.id.content_send);
            contentReceive = itemView.findViewById(R.id.content_receive);
            sendLayout = itemView.findViewById(R.id.send_layout);
            receiveLayout = itemView.findViewById(R.id.receive_layout);
        }
    }

    @NonNull
    @Override
    public ListMessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item,viewGroup,false);
        return new ListMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMessageViewHolder listCommentViewHolder, final int i) {
        Message message = DbContext.getInstance().getListMessages().get(i);
        if(message.type == 0){
            listCommentViewHolder.sendLayout.setVisibility(View.VISIBLE);
            listCommentViewHolder.receiveLayout.setVisibility(View.INVISIBLE);
            listCommentViewHolder.contentSend.setText(message.getContent());
            Log.d(TAG, "onBindViewHolder: "+listCommentViewHolder.contentSend.getText().toString());

            listCommentViewHolder.timeSend.setText(message.getTime());
        }else{
            Log.d(TAG, "onBindViewHolder: "+listCommentViewHolder.contentSend.getText().toString());

            listCommentViewHolder.receiveLayout.setVisibility(View.VISIBLE);
            listCommentViewHolder.sendLayout.setVisibility(View.INVISIBLE);
            listCommentViewHolder.contentReceive.setText(message.getContent());
            listCommentViewHolder.timeReceive.setText(message.getTime());
        }

    }

    @Override
    public int getItemCount() {

        Log.d(TAG, "getItemCount: "+ DbContext.getInstance().getListMessages().size());
        return DbContext.getInstance().getListMessages().size();
    }


}

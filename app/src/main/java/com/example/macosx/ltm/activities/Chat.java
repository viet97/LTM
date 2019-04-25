package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.adapters.ListMessageAdapter;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.ChatService;
import com.example.macosx.ltm.network.api.FriendService;
import com.example.macosx.ltm.network.response.ChatResponse;
import com.example.macosx.ltm.network.response.FriendResponse;
import com.example.macosx.ltm.network.response.VoidResponse;
import com.example.macosx.ltm.ultils.Dialog;
import com.example.macosx.ltm.ultils.Ultils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends Activity {
    private static final String TAG = "CHAT";
    private RecyclerView listMessage;
    private EditText input;
    private TextView name;
    private ImageButton send;
    ImageButton back;
    public static Chat instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        name = findViewById(R.id.name);
        name.setText(Ultils.instance.getNameOfPost(Integer.parseInt(getIntent().getExtras().get("id_receive").toString())));

        input = findViewById(R.id.input);
        send = findViewById(R.id.send_message);

        listMessage = findViewById(R.id.rv_list_message);
        listMessage.setAdapter(new ListMessageAdapter());
        listMessage.setLayoutManager(new LinearLayoutManager(this));
        listMessage.setHasFixedSize(true);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_receive = 0;
                if (getIntent().getExtras().get("id_receive") != null) {
                    id_receive = Integer.parseInt(getIntent().getExtras().get("id_receive").toString());
                }
                ChatService chatService = NetworkManager.getInstance().create(ChatService.class);
                String content = input.getText().toString();
                if (listMessage != null && !content.equals("")) {
                    input.setText("");
                    Date time = Calendar.getInstance().getTime();

                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String formatedTime = df.format(time);
                    chatService.sendMessage(id_receive, DbContext.getInstance().getCurrentUser().getId(), content, formatedTime).enqueue(new Callback<VoidResponse>() {
                        @Override
                        public void onResponse(Call<VoidResponse> call, Response<VoidResponse> response) {
                            VoidResponse voidResponse = response.body();
                            if (!voidResponse.getErrorCode().equals("0")) {
                                Dialog.instance.showMessageDialog(Chat.this, getString(R.string.error), voidResponse.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Call<VoidResponse> call, Throwable throwable) {
                            Dialog.instance.showMessageDialog(Chat.this, getString(R.string.error), getString(R.string.failur_message));

                        }
                    });
                }
            }
        });

        getMessages();

    }

    @Override
    protected void onResume() {
        super.onResume();
        instance = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        instance = null;
    }

    public void scrollToBottom(){
        listMessage.scrollToPosition(DbContext.getInstance().getListMessages().size() - 1);
    }

    public void getMessages() {
        int id_receive = 0;
        if (getIntent().getExtras().get("id_receive") != null) {
            id_receive = Integer.parseInt(getIntent().getExtras().get("id_receive").toString());
        }
        ChatService chatService = NetworkManager.getInstance().create(ChatService.class);
        String url = "messages?id_send=" + DbContext.getInstance().getCurrentUser().getId() + "&id_receive=" + id_receive;
        if (listMessage != null) {
            chatService.getAllMessages(url).enqueue(new Callback<ChatResponse>() {
                @Override
                public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                    ChatResponse chatResponse = response.body();
                    if (chatResponse.getErrorCode().equals("0")) {
                        DbContext.getInstance().setListMessages(chatResponse.getMessages());
                        listMessage.getAdapter().notifyDataSetChanged();
                        scrollToBottom();
                    } else {
                        Dialog.instance.showMessageDialog(Chat.this
                                , getString(R.string.error), chatResponse.getMsg());
                    }
                }

                @Override
                public void onFailure(Call<ChatResponse> call, Throwable throwable) {
                    Dialog.instance.showMessageDialog(Chat.this, getString(R.string.error), getString(R.string.failur_message));

                }
            });
        }
    }

}

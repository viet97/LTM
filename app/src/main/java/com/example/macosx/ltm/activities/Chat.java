package com.example.macosx.ltm.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.adapters.ListMessageAdapter;
import com.example.macosx.ltm.database.DbContext;

public class Chat extends Activity {
    private static final String TAG = "CHAT";
    private RecyclerView listMessage ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listMessage = findViewById(R.id.rv_list_message);
        listMessage.setAdapter(new ListMessageAdapter());
        listMessage.setLayoutManager(new LinearLayoutManager(this));
        listMessage.setHasFixedSize(true);
    }

}

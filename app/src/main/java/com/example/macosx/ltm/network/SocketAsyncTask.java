package com.example.macosx.ltm.network;

import android.os.AsyncTask;

public class SocketAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        try {
            Socket socket =new Socket();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

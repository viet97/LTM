package com.example.macosx.ltm.network;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.macosx.ltm.ultils.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hanhi on 7/14/2017.
 */

public class NetworkManager {
    public static final NetworkManager instance = new NetworkManager();
    private static final String TAG = "NetworkManager";

    public static NetworkManager getInstance() {
        return instance;
    }

    private Retrofit retrofit;
    private long CONNECT_TIMEOUT = 8;
    private long READ_TIMEOUT = 8;
    private String BASE_URL = String.format("%s%s",Constant.BASE_URL,"api/");

    private NetworkManager() {
        try {
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(new LoggerInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } catch (Exception e) {
            Log.d(TAG, "NetworkManager: "+e.toString());
        }
    }



    public class LoggerInterceptor implements Interceptor {

        private static final String TAG = "LoggerInterceptor";

        @Override
        public Response intercept(Chain chain) throws IOException {
            try {
                //1 Get request
                Request request = chain.request();

                //2 Process request (print out)
                Log.d(TAG, String.format("url: %s", request.toString()));

                RequestBody body = request.body();
                if (body != null) {
                    Log.d(TAG, String.format("body: %s", body.toString()));
                }

                Headers headers = request.headers();
                if (headers != null) {
                    Log.d(TAG, String.format(String.format("headers: %s", headers.toString())));
                }

                //3 Proceed
                Response response = chain.proceed(request);

                //4 Process response
                Log.d(TAG, String.format("response: %s", response.toString()));

                Log.d(TAG, String.format("response body: %s", getResponseString(response)));

                return response;

            } catch (SocketTimeoutException e) {
                Log.d(TAG, "intercept: đã quá tgian");
                return chain.proceed(chain.request());
            }
        }
    }


    private String getResponseString(Response response) {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();
        return buffer.clone().readString(Charset.forName("UTF-8"));
    }


    public <T> T create(Class<T> classz) {
        return retrofit.create(classz);
    }
}

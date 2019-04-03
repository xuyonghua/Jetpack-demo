package com.xuyonghua.paging.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.xuyonghua.paging.util.Constants.BASE_URL;
import static com.xuyonghua.paging.util.Constants.TIME_OUT;


public class AppRetrofit {
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppRetrofit sInstance;
    private static Retrofit retrofit;
    private OkHttpClient client;

    public static Retrofit getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppRetrofit();
            }
        }
        return retrofit;
    }

    private AppRetrofit() {
        client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}

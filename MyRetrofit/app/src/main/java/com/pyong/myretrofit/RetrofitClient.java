package com.pyong.myretrofit;

import android.util.TimeUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton
 */
public class RetrofitClient {

    private static RetrofitClient instance;
    private static RetrofitAPI retrofitAPI;

    private final static String BASE_URL = "http://swopenAPI.seoul.go.kr";

    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.SECONDS
                )
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null)
            instance = new RetrofitClient();
        return instance;
    }

    public static RetrofitAPI getRetrofitAPI() {
        return retrofitAPI;
    }

}

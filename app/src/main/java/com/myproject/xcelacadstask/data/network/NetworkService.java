package com.myproject.xcelacadstask.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myproject.xcelacadstask.util.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private final Gson gson;
    private final ApiInterface apiInterface;

    public NetworkService() {
        mInstance = this;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .callTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        gson = new GsonBuilder().setLenient().create();
        apiInterface = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiInterface.class);
    }

    public static NetworkService getInstance() {
        return mInstance;
    }

    public ApiInterface getWebApi() {
        return apiInterface;
    }
}

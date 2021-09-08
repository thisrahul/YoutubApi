package com.myproject.xcelacadstask.data.network;

import com.myproject.xcelacadstask.data.model.Response;
import com.myproject.xcelacadstask.data.model.statistics.ResponseVideoInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<Response> getData(@Url String url);

    @GET
    Call<ResponseVideoInfo> getVideoInfo(@Url String url);
}


package com.myproject.xcelacadstask.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.myproject.xcelacadstask.data.model.Response;
import com.myproject.xcelacadstask.data.model.statistics.ResponseVideoInfo;
import com.myproject.xcelacadstask.data.network.ApiInterface;
import com.myproject.xcelacadstask.data.network.NetworkService;
import com.myproject.xcelacadstask.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchRepository {

    public MutableLiveData<Response> search(String keyword, String pageToken) {

        String url = Constants.BASE_URL + "search?part=snippet&q="
                + keyword
                + "&key="
                + Constants.KEY
                + "&maxResults=" + Constants.max + "&pageToken=" + pageToken + "&type=video";

        MutableLiveData<Response> mutableLiveData = new MutableLiveData<Response>();
        new NetworkService();
        ApiInterface apiInterface = NetworkService.getInstance().getWebApi();
        apiInterface.getData(url)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()) {
                            mutableLiveData.setValue(response.body());
                        } else {
                            mutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        mutableLiveData.setValue(null);
                    }
                });

        return mutableLiveData;
    }

}

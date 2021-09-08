package com.myproject.xcelacadstask.ui.search;

import androidx.lifecycle.MutableLiveData;

import com.myproject.xcelacadstask.data.model.Response;

public interface SearchListener {

    void onStarted();
    void onSuccess(MutableLiveData<Response> searchResponse);
    void onFailure(String msg);
}

package com.myproject.xcelacadstask.ui.search;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myproject.xcelacadstask.data.model.Response;
import com.myproject.xcelacadstask.data.repositories.SearchRepository;

import java.util.Objects;

public class SearchViewModel extends ViewModel implements TextView.OnEditorActionListener{

   public String searchKeyword = "";
   public SearchListener searchListener;
    MutableLiveData<Response> searchResponse;

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        searchListener.onStarted();
        if (searchKeyword.isEmpty()){
            //display error message
            searchListener.onFailure("Please enter keyword to search");
            return false;
        }else {
            SearchRepository searchRepository = new SearchRepository();
            searchResponse = searchRepository.search(searchKeyword, "");
            searchListener.onSuccess(searchResponse);
        }
        return false;
    }

    public void onNext(View v){
        searchListener.onStarted();
        if (searchResponse.getValue().getNextPageToken() != null){
            SearchRepository searchRepository = new SearchRepository();
            searchResponse = searchRepository.search(searchKeyword, Objects.requireNonNull(searchResponse.getValue()).getNextPageToken());
            searchListener.onSuccess(searchResponse);
        }else{
            searchListener.onFailure("No more data to display");
        }
    }

    public void onPrev(View v){
        searchListener.onStarted();
        if (searchResponse.getValue().getPrevPageToken() != null){
            SearchRepository searchRepository = new SearchRepository();
            searchResponse = searchRepository.search(searchKeyword, Objects.requireNonNull(searchResponse.getValue()).getPrevPageToken());
            searchListener.onSuccess(searchResponse);
        }else{
            searchListener.onFailure("No more data to display");
        }
    }
}

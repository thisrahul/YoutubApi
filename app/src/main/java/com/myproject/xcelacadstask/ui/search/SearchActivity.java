package com.myproject.xcelacadstask.ui.search;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.myproject.xcelacadstask.R;
import com.myproject.xcelacadstask.data.model.Response;
import com.myproject.xcelacadstask.databinding.ActivitySearchBinding;
import com.myproject.xcelacadstask.util.Utils;

public class SearchActivity extends AppCompatActivity implements SearchListener {

    private ActivitySearchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        SearchViewModel viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.searchListener = this;

    }

    @Override
    public void onStarted() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.txtResult.setVisibility(View.GONE);
        binding.rvSearchItem.setVisibility(View.GONE);
        binding.rlMove.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(MutableLiveData<Response> searchResponse) {
        searchResponse.observe(this, response -> {
            binding.progressBar.setVisibility(View.GONE);

            try {
                if (response.getItems().size()>0){
                    binding.txtResult.setVisibility(View.GONE);
                    binding.rvSearchItem.setVisibility(View.VISIBLE);
                    binding.rlMove.setVisibility(View.VISIBLE);

                    if (response.getPrevPageToken() == null){
                        binding.btnPrev.setVisibility(View.GONE);
                    }else {
                        binding.btnPrev.setVisibility(View.VISIBLE);
                    }
                    if (response.getNextPageToken() == null){
                        binding.btnNext.setVisibility(View.GONE);
                    }else {
                        binding.btnNext.setVisibility(View.VISIBLE);
                    }

                    SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this,response.getItems());
                    binding.rvSearchItem.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    binding.rvSearchItem.setAdapter(searchAdapter);
                }else {
                    binding.txtResult.setVisibility(View.VISIBLE);
                    binding.rvSearchItem.setVisibility(View.GONE);
                    binding.rlMove.setVisibility(View.GONE);
                    binding.txtResult.setText("No result found");
                }
            }catch (Exception e){
                e.printStackTrace();
                binding.txtResult.setVisibility(View.VISIBLE);
                binding.rvSearchItem.setVisibility(View.GONE);
                binding.rlMove.setVisibility(View.GONE);
                binding.txtResult.setText("No result found");
            }
        });
    }

    @Override
    public void onFailure(String msg) {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtResult.setVisibility(View.VISIBLE);
        binding.rvSearchItem.setVisibility(View.GONE);
        binding.rlMove.setVisibility(View.GONE);
        binding.txtResult.setText("No result found");
    }

}
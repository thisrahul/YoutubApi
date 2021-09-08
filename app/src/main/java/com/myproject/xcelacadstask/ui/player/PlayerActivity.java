package com.myproject.xcelacadstask.ui.player;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.myproject.xcelacadstask.R;
import com.myproject.xcelacadstask.data.model.statistics.ResponseVideoInfo;
import com.myproject.xcelacadstask.data.network.ApiInterface;
import com.myproject.xcelacadstask.data.network.NetworkService;
import com.myproject.xcelacadstask.databinding.ActivityPlayerBinding;
import com.myproject.xcelacadstask.util.Constants;
import com.myproject.xcelacadstask.util.Utils;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;

import static com.myproject.xcelacadstask.util.Utils.format;

public class PlayerActivity extends YouTubeBaseActivity {

    private String videoId, title, description;
    private ActivityPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player);

        videoId = getIntent().getExtras().getString("videoId");
        title = getIntent().getExtras().getString("title");
        description = getIntent().getExtras().getString("description");

        initPlayer();
    }

    private void init() {

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.llDetails.setVisibility(View.GONE);

        binding.txtShare.setOnClickListener(v -> {
            Utils.shareLink(PlayerActivity.this,videoId);
        });

        String url = Constants.BASE_URL + "videos?part=statistics"
                + "&id=" + videoId
                + "&key="
                + Constants.KEY;

        final ResponseVideoInfo[] responseVideoInfo = {new ResponseVideoInfo()};
        new NetworkService();
        ApiInterface apiInterface = NetworkService.getInstance().getWebApi();
        apiInterface.getVideoInfo(url)
                .enqueue(new Callback<ResponseVideoInfo>() {
                    @Override
                    public void onResponse(Call<ResponseVideoInfo> call, retrofit2.Response<ResponseVideoInfo> response) {
                        responseVideoInfo[0] = response.body();

                        binding.progressBar.setVisibility(View.GONE);
                        binding.llDetails.setVisibility(View.VISIBLE);

                        binding.txtTitle.setText(title);
                        binding.txtDescription.setText(description);

                        try {
                            binding.txtViews.setText(format(responseVideoInfo[0].getItems().get(0).getStatistics().getViewCount()));
                            binding.txtLike.setText(format(responseVideoInfo[0].getItems().get(0).getStatistics().getLikeCount()));
                            binding.txtDislike.setText(format(responseVideoInfo[0].getItems().get(0).getStatistics().getDislikeCount()));
                            binding.txtComment.setText(format(responseVideoInfo[0].getItems().get(0).getStatistics().getCommentCount()));
                        } catch (Exception e) {
                            binding.txtViews.setVisibility(View.GONE);
                            binding.txtLike.setVisibility(View.GONE);
                            binding.txtDislike.setVisibility(View.GONE);
                            binding.txtComment.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseVideoInfo> call, Throwable t) {
                        responseVideoInfo[0] = null;
                    }
                });


    }

    private void initPlayer() {
        binding.ytPlayerView.initialize(Constants.KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
                youTubePlayer.play();
                init();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
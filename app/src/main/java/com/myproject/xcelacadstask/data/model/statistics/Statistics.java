package com.myproject.xcelacadstask.data.model.statistics;

import com.google.gson.annotations.SerializedName;

public class Statistics{

	@SerializedName("dislikeCount")
	private String dislikeCount;

	@SerializedName("likeCount")
	private String likeCount;

	@SerializedName("viewCount")
	private String viewCount;

	@SerializedName("favoriteCount")
	private String favoriteCount;

	@SerializedName("commentCount")
	private String commentCount;

	public String getDislikeCount(){
		return dislikeCount;
	}

	public String getLikeCount(){
		return likeCount;
	}

	public String getViewCount(){
		return viewCount;
	}

	public String getFavoriteCount(){
		return favoriteCount;
	}

	public String getCommentCount(){
		return commentCount;
	}
}
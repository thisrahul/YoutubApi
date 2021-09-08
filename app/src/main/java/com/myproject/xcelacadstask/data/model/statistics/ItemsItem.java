package com.myproject.xcelacadstask.data.model.statistics;

import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("kind")
	private String kind;

	@SerializedName("etag")
	private String etag;

	@SerializedName("id")
	private String id;

	@SerializedName("statistics")
	private Statistics statistics;

	public String getKind(){
		return kind;
	}

	public String getEtag(){
		return etag;
	}

	public String getId(){
		return id;
	}

	public Statistics getStatistics(){
		return statistics;
	}
}
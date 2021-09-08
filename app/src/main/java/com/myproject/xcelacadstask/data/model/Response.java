package com.myproject.xcelacadstask.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("prevPageToken")
	private String prevPageToken;

	@SerializedName("regionCode")
	private String regionCode;

	@SerializedName("kind")
	private String kind;

	@SerializedName("nextPageToken")
	private String nextPageToken;

	@SerializedName("pageInfo")
	private PageInfo pageInfo;

	@SerializedName("etag")
	private String etag;

	@SerializedName("items")
	private List<ItemsItem> items;

	public String getPrevPageToken(){
		return prevPageToken;
	}

	public String getRegionCode(){
		return regionCode;
	}

	public String getKind(){
		return kind;
	}

	public String getNextPageToken(){
		return nextPageToken;
	}

	public PageInfo getPageInfo(){
		return pageInfo;
	}

	public String getEtag(){
		return etag;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}
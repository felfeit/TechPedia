package com.felfeit.techpedia.data.datasource.remote.dto;

import com.google.gson.annotations.SerializedName;

public class BrandsDtoItem {

	@SerializedName("brand_slug")
	private String brandSlug;

	@SerializedName("brand_name")
	private String brandName;

	@SerializedName("detail")
	private String detail;

	@SerializedName("device_count")
	private int deviceCount;

	@SerializedName("brand_id")
	private int brandId;

	public String getBrandSlug(){
		return brandSlug;
	}

	public String getBrandName(){
		return brandName;
	}

	public String getDetail(){
		return detail;
	}

	public int getDeviceCount(){
		return deviceCount;
	}

	public int getBrandId(){
		return brandId;
	}
}
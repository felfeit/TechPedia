package com.felfeit.techpedia.data.datasource.remote.dto;

import com.google.gson.annotations.SerializedName;

public class GadgetDtoItem {

	@SerializedName("image")
	private String image;

	@SerializedName("phone_name")
	private String phoneName;

	@SerializedName("detail")
	private String detail;

	@SerializedName("slug")
	private String slug;

	public String getImage(){
		return image;
	}

	public String getPhoneName(){
		return phoneName;
	}

	public String getDetail(){
		return detail;
	}

	public String getSlug(){
		return slug;
	}
}
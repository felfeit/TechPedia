package com.felfeit.techpedia.data.datasource.remote.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BrandsDto{

	@SerializedName("data")
	private List<BrandsDtoItem> data;

	@SerializedName("status")
	private boolean status;

	public List<BrandsDtoItem> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}
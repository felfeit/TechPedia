package com.felfeit.techpedia.data.datasource.remote.dto;

import com.google.gson.annotations.SerializedName;

public class GadgetSpecsDto {

	@SerializedName("data")
	private GadgetSpecsData data;

	@SerializedName("status")
	private boolean status;

	public GadgetSpecsData getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}
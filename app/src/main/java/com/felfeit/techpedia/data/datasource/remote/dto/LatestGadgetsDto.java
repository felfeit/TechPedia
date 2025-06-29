package com.felfeit.techpedia.data.datasource.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LatestGadgetsDto {

	@SerializedName("data")
	private LatestGadgetsDtoData data;

	@SerializedName("status")
	private boolean status;

	public LatestGadgetsDtoData getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}
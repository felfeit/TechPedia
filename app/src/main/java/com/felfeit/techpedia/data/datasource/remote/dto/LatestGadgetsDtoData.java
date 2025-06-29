package com.felfeit.techpedia.data.datasource.remote.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LatestGadgetsDtoData {

	@SerializedName("phones")
	private List<GadgetDtoItem> phones;

	@SerializedName("title")
	private String title;

	public List<GadgetDtoItem> getPhones(){
		return phones;
	}

	public String getTitle(){
		return title;
	}
}
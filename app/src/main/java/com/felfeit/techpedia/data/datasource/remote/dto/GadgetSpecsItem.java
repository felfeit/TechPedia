package com.felfeit.techpedia.data.datasource.remote.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GadgetSpecsItem {

	@SerializedName("val")
	private List<String> val;

	@SerializedName("key")
	private String key;

	public List<String> getVal(){
		return val;
	}

	public String getKey(){
		return key;
	}
}
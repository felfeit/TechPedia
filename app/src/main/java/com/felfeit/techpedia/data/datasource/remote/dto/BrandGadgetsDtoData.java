package com.felfeit.techpedia.data.datasource.remote.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BrandGadgetsDtoData {

	@SerializedName("last_page")
	private int lastPage;

	@SerializedName("phones")
	private List<BrandGadgetsDtoItem> phones;

	@SerializedName("title")
	private String title;

	@SerializedName("current_page")
	private int currentPage;

	public int getLastPage(){
		return lastPage;
	}

	public List<BrandGadgetsDtoItem> getPhones(){
		return phones;
	}

	public String getTitle(){
		return title;
	}

	public int getCurrentPage(){
		return currentPage;
	}
}
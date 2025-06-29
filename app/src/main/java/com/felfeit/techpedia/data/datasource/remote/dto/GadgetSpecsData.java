package com.felfeit.techpedia.data.datasource.remote.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GadgetSpecsData {

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("os")
	private String os;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("phone_images")
	private List<String> phoneImages;

	@SerializedName("phone_name")
	private String phoneName;

	@SerializedName("storage")
	private String storage;

	@SerializedName("brand")
	private String brand;

	@SerializedName("dimension")
	private String dimension;

	@SerializedName("specifications")
	private List<GadgetSpecificationsItem> specifications;

	public String getThumbnail(){
		return thumbnail;
	}

	public String getOs(){
		return os;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public List<String> getPhoneImages(){
		return phoneImages;
	}

	public String getPhoneName(){
		return phoneName;
	}

	public String getStorage(){
		return storage;
	}

	public String getBrand(){
		return brand;
	}

	public String getDimension(){
		return dimension;
	}

	public List<GadgetSpecificationsItem> getSpecifications(){
		return specifications;
	}
}
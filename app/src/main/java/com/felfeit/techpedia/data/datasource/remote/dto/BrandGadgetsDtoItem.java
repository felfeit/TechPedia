package com.felfeit.techpedia.data.datasource.remote.dto;

import com.google.gson.annotations.SerializedName;

public class BrandGadgetsDtoItem {

    @SerializedName("brand")
    private String brand;

    @SerializedName("phone_name")
    private String phoneName;

    @SerializedName("slug")
    private String slug;

    @SerializedName("image")
    private String image;

    @SerializedName("detail")
    private String detail;

    public String getBrand() {
        return brand;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public String getSlug() {
        return slug;
    }

    public String getImage() {
        return image; }

    public String getDetail() { return detail; }
}

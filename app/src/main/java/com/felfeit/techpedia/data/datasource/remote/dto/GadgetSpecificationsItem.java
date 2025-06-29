package com.felfeit.techpedia.data.datasource.remote.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GadgetSpecificationsItem {

    @SerializedName("specs")
    private List<GadgetSpecsItem> specs;

    @SerializedName("title")
    private String title;

    public List<GadgetSpecsItem> getSpecs() {
        return specs;
    }

    public String getTitle() {
        return title;
    }
}
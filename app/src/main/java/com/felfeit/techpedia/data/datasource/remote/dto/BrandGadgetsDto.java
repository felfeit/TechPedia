package com.felfeit.techpedia.data.datasource.remote.dto;

import com.google.gson.annotations.SerializedName;

public class BrandGadgetsDto {

    @SerializedName("data")
    private BrandGadgetsDtoData data;

    @SerializedName("status")
    private boolean status;

    public BrandGadgetsDtoData getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }
}
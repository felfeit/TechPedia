package com.felfeit.techpedia.data.datasource.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "brand")
public class BrandEntity {
    @PrimaryKey
    @ColumnInfo(name = "slug")
    @NonNull
    private String brandSlug;

    @ColumnInfo(name = "brand_name")
    private String brandName;

    @ColumnInfo(name = "device_count")
    private int deviceCount;

    @NonNull
    public String getBrandSlug() {
        return brandSlug;
    }

    public void setBrandSlug(@NonNull String brandSlug) {
        this.brandSlug = brandSlug;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }
}
package com.felfeit.techpedia.data.datasource.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gadget")
public class GadgetEntity {
    @PrimaryKey
    @ColumnInfo(name = "slug")
    @NonNull
    private String slug;

    @ColumnInfo(name = "gadget_name")
    private String gadgetName;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "brand")
    private String brand;

    @ColumnInfo(name = "release_info")
    private String releaseInfo;

    @ColumnInfo(name = "os_info")
    private String osInfo;

    @ColumnInfo(name = "is_saved")
    private boolean isSaved = false;

    @NonNull
    public String getSlug() {
        return slug;
    }

    public void setSlug(@NonNull String slug) {
        this.slug = slug;
    }

    public String getGadgetName() {
        return gadgetName;
    }

    public void setGadgetName(String phoneName) {
        this.gadgetName = phoneName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public String getOsInfo() {
        return osInfo;
    }

    public void setOsInfo(String osInfo) {
        this.osInfo = osInfo;
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
    }
}
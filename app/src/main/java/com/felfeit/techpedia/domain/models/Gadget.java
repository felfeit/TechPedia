package com.felfeit.techpedia.domain.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;



public class Gadget implements Parcelable {
    private String gadgetName;
    private String brand;
    private String slug;
    private String image;
    private String releaseInfo;
    private String osInfo;
    private boolean isSaved;

    public Gadget() {
    }

    protected Gadget(Parcel source) {
        gadgetName = source.readString();
        slug = source.readString();
        image = source.readString();
        brand = source.readString();
        releaseInfo = source.readString();
        osInfo = source.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isSaved = source.readBoolean();
        }
    }

    public static final Creator<Gadget> CREATOR = new Creator<>() {
        @Override
        public Gadget createFromParcel(Parcel source) {
            return new Gadget(source);
        }

        @Override
        public Gadget[] newArray(int size) {
            return new Gadget[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(gadgetName);
        dest.writeString(slug);
        dest.writeString(image);
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


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getGadgetName() {
        return gadgetName;
    }

    public void setGadgetName(String gadgetName) {
        this.gadgetName = gadgetName;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public String getOsInfo() {
        return osInfo;
    }

    public void setOsInfo(String osInfo) {
        this.osInfo = osInfo;
    }
}
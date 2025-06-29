package com.felfeit.techpedia.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Brand implements Parcelable {
    private String name;
    private String slug;
    private int deviceCount;

    public Brand() {
    }

    protected Brand(Parcel source) {
        name = source.readString();
        slug = source.readString();
        deviceCount = source.readInt();
    }

    public static final Creator<Brand> CREATOR = new Creator<>() {
        @Override
        public Brand createFromParcel(Parcel source) {
            return new Brand(source);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeInt(deviceCount);
    }
}

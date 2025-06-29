package com.felfeit.techpedia.domain.models;

import java.util.List;

public class GadgetSpecificationDetail {
    private String brand;
    private String gadgetName;
    private String slug;
    private String thumbnail;
    private String releaseDate;
    private String dimension;
    private String os;
    private String storage;
    private List<String> phoneImages;
    private List<GadgetSpecification> specifications;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGadgetName() {
        return gadgetName;
    }

    public void setGadgetName(String gadgetName) {
        this.gadgetName = gadgetName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public List<String> getPhoneImages() {
        return phoneImages;
    }

    public void setPhoneImages(List<String> phoneImages) {
        this.phoneImages = phoneImages;
    }

    public List<GadgetSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GadgetSpecification> specifications) {
        this.specifications = specifications;
    }
}

package com.felfeit.techpedia.domain.models;

import java.util.List;

public class GadgetSpecification {
    private String title;
    private List<SpecificationDetail> specs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SpecificationDetail> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecificationDetail> specs) {
        this.specs = specs;
    }
}
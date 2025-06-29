package com.felfeit.techpedia.domain.models;

import java.util.List;

public class SpecificationDetail {
    private String key;
    private List<String> val;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getVal() {
        return val;
    }

    public void setVal(List<String> val) {
        this.val = val;
    }
}
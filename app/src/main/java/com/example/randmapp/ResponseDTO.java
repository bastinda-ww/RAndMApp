package com.example.randmapp;

import com.example.randmapp.model.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDTO {
    @SerializedName("info")
    @Expose
    private Object info;
    @SerializedName("results")
    @Expose
    private List<Result> results;

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}

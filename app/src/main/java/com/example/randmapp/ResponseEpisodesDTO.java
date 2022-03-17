package com.example.randmapp;

import com.example.randmapp.model.Episode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEpisodesDTO {
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<Episode> results;

    public Info getInfo() {
        return info;
    }

    public List<Episode> getEpisodes() {
        return results;
    }

}

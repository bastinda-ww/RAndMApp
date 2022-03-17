package com.example.randmapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Episode {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("episode")
    @Expose
    private String episode;
    @SerializedName("characters")
    @Expose
    private List<String> characters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

}

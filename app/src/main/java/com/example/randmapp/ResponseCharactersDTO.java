package com.example.randmapp;

import com.example.randmapp.model.Character;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCharactersDTO {
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<Character> results;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Character> getCharacters() {
        return results;
    }

    public void setCharacters(List<Character> results) {
        this.results = results;
    }
}

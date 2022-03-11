package com.example.randmapp.model;

public class Episode {
    private int id;
    private String name;
    private String airDate;

    public Episode(int id, String name, String airDate) {
        this.id = id;
        this.name = name;
        this.airDate = airDate;
    }

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

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }
}

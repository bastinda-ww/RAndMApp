package com.example.randmapp;

import com.android.volley.toolbox.ImageLoader;

public class Character {
    private int id;
    private String name;
    private String status;
    private String species;
    private Location location;
    private String imageURL;
    private ImageLoader imageLoader;

    public Character(int id, String name, String status, String species, Location location, String imageURL, ImageLoader imageLoader) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.location = location;
        this.imageURL = imageURL;
        this.imageLoader = imageLoader;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String image) {
        this.imageURL = image;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }
}

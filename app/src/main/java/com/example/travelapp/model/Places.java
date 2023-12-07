package com.example.travelapp.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Places implements Serializable {
    private String cityName;
    private String rating;
    private String image;
    private boolean fav;

    private String description;

    public Places(String cityName, String rating, String image, boolean fav, String description) {
        this.cityName = cityName;
        this.rating = rating;
        this.image = image;
        this.fav = fav;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}

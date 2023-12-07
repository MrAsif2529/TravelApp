package com.example.travelapp.model;

import androidx.annotation.NonNull;

public class Places {
    private String cityName;
    private String rating;
    private String image;
    private boolean fav;

    public Places(String cityName, String rating, String image, boolean fav) {
        this.cityName = cityName;
        this.rating = rating;
        this.image = image;
        this.fav = fav;
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

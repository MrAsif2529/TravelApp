package com.example.travelapp.model;

import java.io.Serializable;

public class Places implements Serializable {
    private String cityName;
    private String rating;
    private String image;
    private boolean fav;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String description;
    private String favId;

    private CityKeys cityKeys;

    public void setCityKeys(CityKeys cityKeys) {
        this.cityKeys = cityKeys;
    }

    public CityKeys getCityKeys() {
        return cityKeys;
    }

    public void setFavId(String favId) {
        this.favId = favId;
    }

    public String getFavId() {
        return favId;
    }

    public Places(String cityName, String rating, String image, boolean fav, String description, double latitude, double longitude) {
        this.cityName = cityName;
        this.rating = rating;
        this.image = image;
        this.fav = fav;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
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

    @Override
    public String toString() {
        return "Places{" +
                "cityName='" + cityName + '\'' +
                ", rating='" + rating + '\'' +
                ", image='" + image + '\'' +
                ", fav=" + fav +
                ", description='" + description + '\'' +
                ", favId='" + favId + '\'' +
                ", cityKeys=" + cityKeys +
                '}';
    }
}

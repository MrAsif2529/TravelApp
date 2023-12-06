package com.example.travelapp.model;

public class RecommendLocations {


    private String recCityName;
    private int cityCode;
    private String recRating;
    private int recCityImage;
    private boolean fav;

    public RecommendLocations(String recCityName, int recCityImage, int cityCode, boolean fav) {
        this.recCityName = recCityName;
        this.recCityImage = recCityImage;
        this.cityCode = cityCode;
        this.fav = fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public boolean isFav() {
        return fav;
    }

    public String getRecCityName() {
        return recCityName;
    }

    public int getRecCityImage() {
        return recCityImage;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}

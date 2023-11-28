package com.example.travelapp;

public class RecRecyclerViewItem {


    private String recCityName;
    private int cityCode;
    private String recRating;
    private  int recCityImage;

    public RecRecyclerViewItem(String recCityName, int recCityImage,int cityCode){
    this.recCityName= recCityName;
    this.recCityImage=recCityImage;
    this.cityCode = cityCode;

    }

    public String getRecCityName() {
        return recCityName;
    }
    public int getRecCityImage(){ return  recCityImage;}
    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}

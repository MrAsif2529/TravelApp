package com.example.travelapp;

public class StateRecyclerViewItem {


    private String cityname;
    private String cityRating;
    private  int cityimage;

    public StateRecyclerViewItem(String cityname, int cityimage){
    this.cityname= cityname;
    this.cityimage=cityimage;

    }

    public String getStateCityName() {
        return cityname;
    }
    public int getCityImage(){ return  cityimage;}
}

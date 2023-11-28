package com.example.travelapp;

import android.app.Activity;

public class PopularRecyclerViewItem  {


    private String popularcityname;
    private String cityrating;
    private  int cityimage;

    public PopularRecyclerViewItem(String popularcityname,int cityimage){
    this.popularcityname= popularcityname;
    this.cityimage=cityimage;

    }

    public String getPopularCityName() {
        return popularcityname;
    }
    public int getCityImage(){ return  cityimage;}
}

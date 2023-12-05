package com.example.travelapp;

import android.app.Activity;

public class FavoritesRecyclerViewItem  {

    private String favcityname;
    private String favcityrating;
    private  int favcityimage;

    public FavoritesRecyclerViewItem(String favcityname,int favcityimage){
        this.favcityname= favcityname;
        this.favcityimage= favcityimage;

    }

    public String getFavCityName() {
        return favcityname;
    }
    public int getFavCityImage(){ return  favcityimage;}
}


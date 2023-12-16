package com.example.travelapp.model;

import java.io.Serializable;

public class CityKeys implements Serializable {
    public CityKeys(String cityId, String placeId) {
        this.cityId = cityId;
        this.placeId = placeId;
    }

    private String cityId;
    private String placeId;

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getCityId() {
        return cityId;
    }

    public String getPlaceId() {
        return placeId;
    }

    @Override
    public String toString() {
        return "CityKeys{" +
                "cityId='" + cityId + '\'' +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}

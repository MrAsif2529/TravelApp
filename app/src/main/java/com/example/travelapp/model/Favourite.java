package com.example.travelapp.model;

public class Favourite {
    private String favId;
    private String favKey;

    public void setFavId(String favId) {
        this.favId = favId;
    }

    public Favourite(String favId, String favKey) {
        this.favId = favId;
        this.favKey = favKey;
    }

    public void setFavKey(String favKey) {
        this.favKey = favKey;
    }

    public String getFavId() {
        return favId;
    }

    public String getFavKey() {
        return favKey;
    }
}

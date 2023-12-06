package com.example.travelapp.fragments;

import static com.example.travelapp.utils.Constants.ABUDABI;
import static com.example.travelapp.utils.Constants.DUBAI;
import static com.example.travelapp.utils.Constants.SHARJAH;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.travelapp.R;
import com.example.travelapp.model.RecommendLocations;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favourites_fragment, container, false);

        return view;
    }

    private List<RecommendLocations> getCitiesList() {
        List<RecommendLocations> citydata = new ArrayList<>(); // Your data list
        citydata.add(new RecommendLocations("Dubai", R.drawable.burj_khalifa, DUBAI, true));
        citydata.add(new RecommendLocations("Dubaix", R.drawable.populard, DUBAI, true));
        citydata.add(new RecommendLocations("Dubaiy", R.drawable.burj_khalifa, DUBAI, true));

        citydata.add(new RecommendLocations("Abudhabi", R.drawable.populard, ABUDABI, true));
        citydata.add(new RecommendLocations("ABUDHABI", R.drawable.burj_khalifa, ABUDABI, true));
        citydata.add(new RecommendLocations("SHARJAH", R.drawable.burj_khalifa, SHARJAH, true));
        citydata.add(new RecommendLocations("Sharjah", R.drawable.populard, SHARJAH, true));

        return citydata;
    }

}
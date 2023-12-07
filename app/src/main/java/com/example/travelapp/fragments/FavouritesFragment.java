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
import com.example.travelapp.model.Places;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favourites_fragment, container, false);

        return view;
    }


}
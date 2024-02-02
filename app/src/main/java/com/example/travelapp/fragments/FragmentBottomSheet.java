package com.example.travelapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.travelapp.R;
import com.example.travelapp.database.FirebaseHelper;

import java.util.HashMap;


public class FragmentBottomSheet extends Fragment {

    private static final String TAG = "FragmentBottomSheet";
    private String city;
    private String id;

    private FirebaseHelper mFirebaseHelper = new FirebaseHelper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        RatingBar ratingBar = view.findViewById(R.id.ratingBar);

        view.findViewById(R.id.appCompatButton).setOnClickListener(view1 -> {
            float rating = ratingBar.getRating();

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("rating", rating);

            mFirebaseHelper.addRatings(city, id, rating).addOnCompleteListener(task -> {
                Toast.makeText(inflater.getContext(), "Rating added successfully.", Toast.LENGTH_SHORT).show();
            });
            Log.d(TAG, "rating " + rating);
        });

        return view;
    }

    public void show(String city, String id) {
        this.city = city;
        this.id = id;
    }
}
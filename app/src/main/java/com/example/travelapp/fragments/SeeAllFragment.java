package com.example.travelapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.activities.MainActivity;
import com.example.travelapp.adapters.StatesAdapter;
import com.example.travelapp.database.FirebaseHelper;
import com.example.travelapp.model.Places;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SeeAllFragment extends Fragment {
    private List<Places> itemsList = new ArrayList<>();
    private RecyclerView stateRecyclerView;

    private FirebaseHelper mFirebaseHelper = new FirebaseHelper();

    private StatesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_state_places, container, false);
        stateRecyclerView = view.findViewById(R.id.statesRecyclerView);
        int spanCount = 2;
        stateRecyclerView.setLayoutManager(new GridLayoutManager(inflater.getContext(), spanCount));

        adapter = new StatesAdapter(itemsList, inflater.getContext());
        stateRecyclerView.setAdapter(adapter);
        MainActivity search = (MainActivity) getActivity();
        search.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        readAll();
    }

    private void readAll() {
        mFirebaseHelper.readChild("", new FirebaseHelper.IFirebaseHelper() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                itemsList.clear();
                List<Places> places = new ArrayList<>();
                for (DataSnapshot snap : snapshot.getChildren())
                    places.addAll(filterCity(snap));

                adapter.refresh(places);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private static final String TAG = "SeeAllFragment";

    private List<Places> filterCity(DataSnapshot dataSnapshot) {
        List<Places> places = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            if (snapshot.getKey() == null) continue;

            DataSnapshot data = dataSnapshot.child(snapshot.getKey());
            String description = data.child("description").getValue().toString();
            String image = data.child("image").getValue().toString();
            String name = data.child("name").getValue().toString();
            String rating = "0.0";

            if (data.hasChild("ratings")) rating = getRatings(data.child("ratings"));

            Places item = new Places(name, rating, image, false, description,0.0,0.0);
            places.add(item);

        }

        return places;
    }

    private String getRatings(DataSnapshot ratings) {
        float rating = 0.0f;
        for (DataSnapshot snapshot : ratings.getChildren())
            rating = rating + Float.parseFloat(ratings.child(snapshot.getKey()).child("rating").getValue().toString());

        return Float.toString(rating);
    }

}
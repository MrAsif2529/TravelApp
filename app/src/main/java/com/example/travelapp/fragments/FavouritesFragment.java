package com.example.travelapp.fragments;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.adapters.FavoritesAdapter;
import com.example.travelapp.database.FirebaseHelper;
import com.example.travelapp.model.Favourite;
import com.example.travelapp.model.Places;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavouritesFragment extends Fragment {

    private FirebaseHelper mFireHelper = new FirebaseHelper();

    private FavoritesAdapter mFavAdapter;
    private final List<Places> mPlaces = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favourites_fragment, container, false);

        mFavAdapter = new FavoritesAdapter(mPlaces);
        RecyclerView mFavRecyclerview = view.findViewById(R.id.favoritesRecyclerView);

        mFavRecyclerview.setLayoutManager(new LinearLayoutManager(inflater.getContext(), HORIZONTAL, false));
        mFavRecyclerview.setAdapter(mFavAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        readFav();
    }

    private void readFav() {
        mFireHelper.readFav(new FirebaseHelper.IFirebaseHelper() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                List<Favourite> fav = new ArrayList<>();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    String favid = snapshot.child(snap.getKey()).child("place_id").getValue().toString();
                    fav.add(new Favourite(favid, snap.getKey()));
                }

                mFireHelper.readChild("", new FirebaseHelper.IFirebaseHelper() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        updateFavList(fav, snapshot);
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void updateFavList(List<Favourite> fav, DataSnapshot dataSnapshot) {
        List<Places> places = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.getChildren())
            for (DataSnapshot data : snapshot.getChildren()) {
                if (contains(fav, data.getKey()) != -1) {
                    String description = data.child("description").getValue().toString();
                    String image = data.child("image").getValue().toString();
                    String name = data.child("name").getValue().toString();
                    String rating = "0.0";

                    if (data.hasChild("ratings")) rating = getRatings(data.child("ratings"));

                    Places item = new Places(name, rating, image, false, description,0.0,0.0,0);
                    item.setFavId(fav.get(contains(fav, data.getKey())).getFavKey());
                    places.add(item);
                }

            }

        mFavAdapter.refresh(places);
    }

    private int contains(List<Favourite> favourites, String key) {
        for (int i = 0; i < favourites.size(); i++) {
            Favourite favourite = favourites.get(i);
            if (Objects.equals(favourite.getFavId(), key)) return i;
        }

        return -1;
    }

    private String getRatings(DataSnapshot ratings) {
        float rating = 0.0f;
        for (DataSnapshot snapshot : ratings.getChildren())
            rating = rating + Float.parseFloat(ratings.child(snapshot.getKey()).child("rating").getValue().toString());

        return Float.toString(rating);
    }

    private static final String TAG = "FavouritesFragment";
}
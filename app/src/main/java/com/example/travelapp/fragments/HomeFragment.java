package com.example.travelapp.fragments;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static com.example.travelapp.utils.Constants.ABUDABI;
import static com.example.travelapp.utils.Constants.AJMAN;
import static com.example.travelapp.utils.Constants.DUBAI;
import static com.example.travelapp.utils.Constants.FAJOURIA;
import static com.example.travelapp.utils.Constants.RASUL_ALHAIMA;
import static com.example.travelapp.utils.Constants.SHARJAH;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.adapters.PopulorAdapter;
import com.example.travelapp.adapters.RecAdapter;
import com.example.travelapp.database.FirebaseHelper;
import com.example.travelapp.model.CityKeys;
import com.example.travelapp.model.Places;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView dubai, abudhabi, sharjah, ajman, khaima, fujariah, seeAll;

    private RecyclerView popularRecyclerView;
    private RecyclerView recRecyclerView;

    private PopulorAdapter mPopularAdapter;

    private RecAdapter mRecAdapter;
    private String selectedList = ABUDABI;

    private final List<String> favList = new ArrayList<>();
    private static final String TAG = "HomeFragment";

    private final List<Places> mPlaces = new ArrayList<>();
    private final FirebaseHelper mFirebaseHelper = new FirebaseHelper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        TextView seeAll = view.findViewById(R.id.seeAll);

        seeAll.setOnClickListener(v -> {
            SeeAllFragment fragment = new SeeAllFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });

        recRecyclerView = view.findViewById(R.id.recRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(inflater.getContext(), HORIZONTAL, false);
        recRecyclerView.setLayoutManager(layoutManager);


        mRecAdapter = new RecAdapter(mPlaces, new RecAdapter.IRecommendListener() {
            @Override
            public void onClick(Places item) {
                Log.d(TAG, "open details view activity");
            }

            @Override
            public void onFave(Places item) {
                favList.add(item.getCityName());
                Log.d(TAG, "onFave selected");
            }
        }, favList);

        recRecyclerView.setAdapter(mRecAdapter);
        dubai = view.findViewById(R.id.dubai);

        dubai.setOnClickListener(v -> {
            changeBackground(dubai);
            selectedList = DUBAI;
            fetchPlace(DUBAI);
        });

        abudhabi = view.findViewById(R.id.abudhabi);
        abudhabi.setOnClickListener(view1 -> {
            selectedList = ABUDABI;
            changeBackground(abudhabi);
            fetchPlace(ABUDABI);
        });

        sharjah = view.findViewById(R.id.sharjah);
        sharjah.setOnClickListener(view12 -> {
            selectedList = SHARJAH;
            changeBackground(sharjah);
            fetchPlace(SHARJAH);

        });

        ajman = view.findViewById(R.id.ajman);
        ajman.setOnClickListener(view13 -> {
            selectedList = AJMAN;
            changeBackground(ajman);
            fetchPlace(AJMAN);

        });

        khaima = view.findViewById(R.id.khaimah);
        khaima.setOnClickListener(view14 -> {
            selectedList = RASUL_ALHAIMA;
            changeBackground(khaima);
            fetchPlace(RASUL_ALHAIMA);

        });

        fujariah = view.findViewById(R.id.fujariah);
        fujariah.setOnClickListener(view15 -> {
            selectedList = FAJOURIA;
            changeBackground(fujariah);
            fetchPlace(FAJOURIA);

        });


        popularRecyclerView = view.findViewById(R.id.popularRecyclerView);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), HORIZONTAL, false));

        mPopularAdapter = new PopulorAdapter(mPlaces);
        popularRecyclerView.setAdapter(mPopularAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseHelper.readFav(new FirebaseHelper.IFirebaseHelper() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    String fav = snapshot.child(snap.getKey()).child("place_id").getValue().toString();
                    favList.add(fav);
                }
                fetchPlace("dubai");
            }

            @Override
            public void onError(String message) {
                fetchPlace("dubai");

            }
        });
    }


    private void fetchPlace(String place) {
        mFirebaseHelper.readChild(place, new FirebaseHelper.IFirebaseHelper() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                Log.d(TAG, "onSuccess: " + snapshot.toString());
                List<Places> places = filterCity(snapshot);
                mRecAdapter.refresh(places);

                mPopularAdapter.refresh(places);
            }

            @Override
            public void onError(String message) {
                Log.w(TAG, "onError: " + message);
            }
        });
    }

    private void changeBackground(TextView textView) {
        dubai.setTextColor(getResources().getColor(R.color.Gray));
        dubai.setBackgroundResource(R.drawable.round_transparent_bg);
        abudhabi.setTextColor(getResources().getColor(R.color.Gray));
        abudhabi.setBackgroundResource(R.drawable.round_transparent_bg);
        sharjah.setTextColor(getResources().getColor(R.color.Gray));
        sharjah.setBackgroundResource(R.drawable.round_transparent_bg);
        ajman.setTextColor(getResources().getColor(R.color.Gray));
        ajman.setBackgroundResource(R.drawable.round_transparent_bg);
        khaima.setTextColor(getResources().getColor(R.color.Gray));
        khaima.setBackgroundResource(R.drawable.round_transparent_bg);
        fujariah.setTextColor(getResources().getColor(R.color.Gray));
        fujariah.setBackgroundResource(R.drawable.round_transparent_bg);

        textView.setTextColor(getResources().getColor(R.color.Blue));
        textView.setBackgroundResource(R.drawable.edit_text_bg);
    }


    private List<Places> filterCity(DataSnapshot dataSnapshot) {
        List<Places> places = new ArrayList<>();

        Log.d(TAG, "cityKey ---> " + dataSnapshot.getKey());

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            if (snapshot.getKey() == null) continue;

            Log.d(TAG, "cityKey" + snapshot.getKey());

            DataSnapshot data = dataSnapshot.child(snapshot.getKey());
            String description = data.child("description").getValue().toString();
            String image = data.child("image").getValue().toString();
            String name = data.child("name").getValue().toString();

            double latitude = 0.0;
            if (data.hasChild("latitude"))
                latitude = (double) data.child("latitude").getValue();

            double longitude = 0.0;

            if (data.hasChild("longitude"))
                longitude = (double) data.child("longitude").getValue();

            String rating = "0.0";

            if (data.hasChild("ratings")) rating = getRatings(data.child("ratings"));

            boolean fav = favList.contains(snapshot.getKey());
            Places item = new Places(name, rating, image, fav, description, latitude, longitude);

            CityKeys cityKeys = new CityKeys(dataSnapshot.getKey(), snapshot.getKey());
            item.setCityKeys(cityKeys);
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



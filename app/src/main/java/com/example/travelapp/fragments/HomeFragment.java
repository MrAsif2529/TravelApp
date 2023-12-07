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
import com.example.travelapp.model.Places;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView dubai, abudhabi, sharjah, ajman, khaima, fujariah, seeAll;

    private RecyclerView popularRecyclerView;
    private RecyclerView recRecyclerView;

    private RecAdapter mRecAdapter;
    private String selectedList = ABUDABI;

    private List<String> favList = new ArrayList<>();
    private static final String TAG = "HomeFragment";

    private List<Places> mPlaces = new ArrayList<>();
    private FirebaseHelper mFirebaseHelper = new FirebaseHelper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        TextView seeAll = view.findViewById(R.id.seeAll);

        seeAll.setOnClickListener(v -> {
         /*   SeeAllFragment fragment = new SeeAllFragment();
            fragment.setItemsList(filterCity(selectedList));
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();*/
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

        PopulorAdapter adapter = new PopulorAdapter(mPlaces);
        popularRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchPlace("dubai");
    }


    private void fetchPlace(String place) {
        mFirebaseHelper.readChild(place, new FirebaseHelper.IFirebaseHelper() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                Log.d(TAG, "onSuccess: " + snapshot.toString());
                List<Places> places = filterCity(snapshot, place);
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


    private List<Places> filterCity(DataSnapshot dataSnapshot, String place) {
        List<Places> places = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            if (snapshot.getKey() == null) continue;

            if (snapshot.getKey().equals(place))
                Log.d(TAG, "filterCity found " + snapshot.getKey());
        }

        return new ArrayList<>();
    }


}



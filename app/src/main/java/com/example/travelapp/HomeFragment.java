package com.example.travelapp;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static com.example.travelapp.Constants.ABUDABI;
import static com.example.travelapp.Constants.AJMAN;
import static com.example.travelapp.Constants.DUBAI;
import static com.example.travelapp.Constants.FAJOURIA;
import static com.example.travelapp.Constants.RASUL_ALHAIMA;
import static com.example.travelapp.Constants.SHARJAH;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView dubai, abudhabi, sharjah, ajman, khaima, fujariah, seeAll;

    private RecyclerView popularRecyclerView;
    private RecyclerView recRecyclerView;

    private RecAdapter mRecAdapter;
    private int selectedList = ABUDABI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        TextView seeAll = view.findViewById(R.id.seeAll);

        seeAll.setOnClickListener(v -> {
            SeeAllFragment fragment = new SeeAllFragment();
            fragment.setItemsList(filterCity(selectedList));

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        });

        recRecyclerView = view.findViewById(R.id.recRecyclerView);
        LinearLayoutManager layoutManagerx = new LinearLayoutManager(inflater.getContext(), HORIZONTAL, false);
        recRecyclerView.setLayoutManager(layoutManagerx);


        mRecAdapter = new RecAdapter(filterCity(DUBAI));
        recRecyclerView.setAdapter(mRecAdapter);


        dubai = view.findViewById(R.id.dubai);
        dubai.setOnClickListener(v -> {
            changeBackground(dubai);
            selectedList = DUBAI;
            mRecAdapter.refresh(filterCity(DUBAI));
        });

        abudhabi = view.findViewById(R.id.abudhabi);
        abudhabi.setOnClickListener(view1 -> {
            selectedList = ABUDABI;
            changeBackground(abudhabi);
            mRecAdapter.refresh(filterCity(ABUDABI));
        });

        sharjah = view.findViewById(R.id.sharjah);
        sharjah.setOnClickListener(view12 -> {
            selectedList = SHARJAH;
            changeBackground(sharjah);
            mRecAdapter.refresh(filterCity(SHARJAH));

        });

        ajman = view.findViewById(R.id.ajman);
        ajman.setOnClickListener(view13 -> {
            selectedList = AJMAN;
            changeBackground(ajman);
            mRecAdapter.refresh(filterCity(AJMAN));

        });

        khaima = view.findViewById(R.id.khaimah);
        khaima.setOnClickListener(view14 -> {
            selectedList = RASUL_ALHAIMA;
            changeBackground(khaima);
            mRecAdapter.refresh(filterCity(RASUL_ALHAIMA));

        });

        fujariah = view.findViewById(R.id.fujariah);
        fujariah.setOnClickListener(view15 -> {
            selectedList = FAJOURIA;
            changeBackground(fujariah);
            mRecAdapter.refresh(filterCity(FAJOURIA));

        });


        popularRecyclerView = view.findViewById(R.id.popularRecyclerView);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), HORIZONTAL, false));

        PopulorAdapter adapter = new PopulorAdapter(dubai());
        popularRecyclerView.setAdapter(adapter);

        return view;
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


    private List<PopularRecyclerViewItem> dubai() {
        List<PopularRecyclerViewItem> data = new ArrayList<>(); // Your data list
        data.add(new PopularRecyclerViewItem("Burj ul Khalifa", R.drawable.burj_khalifa));
        data.add(new PopularRecyclerViewItem("Burj ul Arab", R.drawable.populard));
        return data;
    }

    private List<PopularRecyclerViewItem> abudhabi() {
        List<PopularRecyclerViewItem> data = new ArrayList<>(); // Your data list
        data.add(new PopularRecyclerViewItem("abu dhabi", R.drawable.burj_khalifa));
        data.add(new PopularRecyclerViewItem("ab dhabi2", R.drawable.populard));
        return data;
    }

    private List<PopularRecyclerViewItem> sharjah() {
        List<PopularRecyclerViewItem> data = new ArrayList<>(); // Your data list
        data.add(new PopularRecyclerViewItem("sharjah", R.drawable.burj_khalifa));
        data.add(new PopularRecyclerViewItem("sharjah2", R.drawable.populard));
        return data;
    }

    private List<PopularRecyclerViewItem> ajman() {
        List<PopularRecyclerViewItem> data = new ArrayList<>(); // Your data list
        data.add(new PopularRecyclerViewItem("ajman", R.drawable.burj_khalifa));
        data.add(new PopularRecyclerViewItem("ajman2", R.drawable.populard));
        return data;
    }

    private List<PopularRecyclerViewItem> rasalkhaima() {
        List<PopularRecyclerViewItem> data = new ArrayList<>(); // Your data list
        data.add(new PopularRecyclerViewItem("Ras Al Khaimah", R.drawable.burj_khalifa));
        data.add(new PopularRecyclerViewItem("RAS AL Khaimah", R.drawable.populard));
        return data;
    }

    private List<PopularRecyclerViewItem> fujariah() {
        List<PopularRecyclerViewItem> data = new ArrayList<>(); // Your data list
        data.add(new PopularRecyclerViewItem("fujariah", R.drawable.burj_khalifa));
        data.add(new PopularRecyclerViewItem("Fujariah", R.drawable.populard));
        return data;
    }


    private List<RecRecyclerViewItem> getCitiesList() {
        List<RecRecyclerViewItem> citydata = new ArrayList<>(); // Your data list
        citydata.add(new RecRecyclerViewItem("Dubai", R.drawable.burj_khalifa, DUBAI));
        citydata.add(new RecRecyclerViewItem("Dubaix", R.drawable.populard, DUBAI));
        citydata.add(new RecRecyclerViewItem("Dubaiy", R.drawable.burj_khalifa, DUBAI));

        citydata.add(new RecRecyclerViewItem("Abudhabi", R.drawable.populard, ABUDABI));
        citydata.add(new RecRecyclerViewItem("ABUDHABI", R.drawable.burj_khalifa, ABUDABI));

        citydata.add(new RecRecyclerViewItem("SHARJAH", R.drawable.burj_khalifa, SHARJAH));
        citydata.add(new RecRecyclerViewItem("Sharjah", R.drawable.populard, SHARJAH));

        citydata.add(new RecRecyclerViewItem("Ajman", R.drawable.populard, AJMAN));
        citydata.add(new RecRecyclerViewItem("AJMAN", R.drawable.burj_khalifa, AJMAN));

        citydata.add(new RecRecyclerViewItem("Ras al Khaima", R.drawable.populard, RASUL_ALHAIMA));
        citydata.add(new RecRecyclerViewItem("Burj ul Khalifa", R.drawable.burj_khalifa, RASUL_ALHAIMA));

        citydata.add(new RecRecyclerViewItem("Burj ul Arab", R.drawable.populard, FAJOURIA));
        citydata.add(new RecRecyclerViewItem("Burj ul Khalifa", R.drawable.burj_khalifa, FAJOURIA));
        return citydata;
    }

    private List<RecRecyclerViewItem> filterCity(int city) {
        List<RecRecyclerViewItem> cities = getCitiesList();
        List<RecRecyclerViewItem> citydata = new ArrayList<>(); // Your data list

        for (RecRecyclerViewItem item : cities)
            if (item.getCityCode() == city) citydata.add(item);

        return citydata;
    }

}



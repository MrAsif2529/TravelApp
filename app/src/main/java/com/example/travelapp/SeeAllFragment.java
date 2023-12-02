package com.example.travelapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SeeAllFragment extends Fragment {
    private List<RecRecyclerViewItem> itemsList;
    private RecyclerView stateRecyclerView;

    public List<RecRecyclerViewItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<RecRecyclerViewItem> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_state_places, container, false);
        stateRecyclerView = view.findViewById(R.id.statesRecyclerView);
        int spanCount = 2;
        stateRecyclerView.setLayoutManager(new GridLayoutManager(inflater.getContext(), spanCount));

        StatesAdapter adapter = new StatesAdapter(itemsList, inflater.getContext());
        stateRecyclerView.setAdapter(adapter);
        return view;
    }
}
package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class StatePlacesActivity extends AppCompatActivity {
    private RecyclerView stateRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_places);

        stateRecyclerView = findViewById(R.id.statesRecyclerView);
        int spanCount = 2;
        stateRecyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        // populorRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Use GridLayoutManager for grid layout
     //   stateRecyclerView.setLayoutManager(grid);

        List<StateRecyclerViewItem> statesdata = generateItemList(); // Method to generate or fetch your data
        StatesAdapter adapter = new StatesAdapter(statesdata,this);
        stateRecyclerView.setAdapter(adapter);


    }
    private List<StateRecyclerViewItem> generateItemList() {
        List<StateRecyclerViewItem> statesdata = new ArrayList<>();
        statesdata.add(new StateRecyclerViewItem("Item 1", R.drawable.dubaidd));
        statesdata.add(new StateRecyclerViewItem("Item 2", R.drawable.burj_khalifa));
        statesdata.add(new StateRecyclerViewItem("Item 1", R.drawable.dubaidd));
        statesdata.add(new StateRecyclerViewItem("Item 2", R.drawable.burj_khalifa));
        statesdata.add(new StateRecyclerViewItem("Item 1", R.drawable.dubaidd));
        statesdata.add(new StateRecyclerViewItem("Item 2", R.drawable.burj_khalifa));
        // Add more items as needed
        return statesdata;

     //   Intent intent = getIntent();
}
}
package com.example.travelapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.StatesViewHolder> {
    private final Context context;
    private List<RecRecyclerViewItem> statesdata;

    public StatesAdapter(List<RecRecyclerViewItem> statesdata, Context context) {
        this.statesdata = statesdata;
        this.context = context;

    }

    @NonNull
    @Override
    public StatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.states_recyclerview_item, parent, false);
        return new StatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatesViewHolder holder, int position) {
        RecRecyclerViewItem clickedItem = statesdata.get(position);

        holder.cityname.setText(clickedItem.getRecCityName());
        holder.cityimage.setImageResource(clickedItem.getRecCityImage());

        holder.itemView.setOnClickListener(view -> {
            // Retrieve the clicked item data

            // Start the new activity here, passing data if needed
            Intent intent = new Intent(context, PlaceViewActivity.class);
            intent.putExtra("cityname", clickedItem.getRecCityName());
            intent.putExtra("cityimage", clickedItem.getRecCityImage());
//                intent.putExtra("citydetails", clickedItem.getCityDetails());// Example data passing
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return statesdata.size();
    }


    public static class StatesViewHolder extends RecyclerView.ViewHolder {
        ImageView cityimage;
        private TextView cityname;

        public StatesViewHolder(@NonNull View itemView) {
            super(itemView);
            cityname = itemView.findViewById(R.id.cityname);
            cityimage = itemView.findViewById(R.id.cityimage);
        }
    }
}

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
    private List<StateRecyclerViewItem> statesdata;
    private final Context context;
    public StatesAdapter(List<StateRecyclerViewItem> statesdata,Context context) {
        this.statesdata= statesdata;
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
        StateRecyclerViewItem stateRecyclerViewItem = statesdata.get(position);
        holder.cityname.setText(stateRecyclerViewItem.getStateCityName());
        holder.cityimage.setImageResource(stateRecyclerViewItem.getCityImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the clicked item data
                StateRecyclerViewItem clickedItem = statesdata.get(position);

                // Start the new activity here, passing data if needed
                Intent intent = new Intent(context, PlaceViewActivity.class);
                intent.putExtra("cityname",  clickedItem.getStateCityName());
                intent.putExtra("cityimage", clickedItem.getCityImage());
                intent.putExtra("citydetails", clickedItem.getCityDetails());// Example data passing
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return statesdata.size();
    }


    public static class StatesViewHolder extends RecyclerView.ViewHolder {
        private TextView cityname;
        ImageView cityimage;

        public StatesViewHolder(@NonNull View itemView) {
            super(itemView);
            cityname = itemView.findViewById(R.id.cityname);
            cityimage = itemView.findViewById(R.id.cityimage);
        }
    }
}

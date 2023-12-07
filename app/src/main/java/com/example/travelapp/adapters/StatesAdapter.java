package com.example.travelapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.model.Places;
import com.example.travelapp.activities.PlaceViewActivity;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.StatesViewHolder> {
    private final Context context;
    private List<Places> statesdata;

    public StatesAdapter(List<Places> statesdata, Context context) {
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
        Places clickedItem = statesdata.get(position);

        holder.cityname.setText(clickedItem.getCityName());
//        holder.cityimage.setImageResource(clickedItem.getImage());


        holder.itemView.setOnClickListener(view -> {
            // Retrieve the clicked item data

            // Start the new activity here, passing data if needed
            Intent intent = new Intent(context, PlaceViewActivity.class);
            intent.putExtra("cityname", clickedItem.getCityName());
            intent.putExtra("cityimage", clickedItem.getImage());
//         intent.putExtra("citydetails", clickedItem.getCityDetails());// Example data passing
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
        private TextView cityname, cityDetails;

        public StatesViewHolder(@NonNull View itemView) {
            super(itemView);
            cityname = itemView.findViewById(R.id.cityname);
            cityimage = itemView.findViewById(R.id.cityimage);
//            cityDetails = itemView.findViewById(R.id.statecityDetails);
        }
    }
}

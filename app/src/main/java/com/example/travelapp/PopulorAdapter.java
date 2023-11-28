package com.example.travelapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PopulorAdapter extends RecyclerView.Adapter<PopulorAdapter.PopularViewHolder> {
    private List<PopularRecyclerViewItem> data;
    public PopulorAdapter(List<PopularRecyclerViewItem> data) {
        this.data= data;

    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_recyclerview_item, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        PopularRecyclerViewItem popularRecyclerViewItem = data.get(position);
        holder.popularcityname.setText(popularRecyclerViewItem.getPopularCityName());
        holder.cityimage.setImageResource(popularRecyclerViewItem.getCityImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        private TextView popularcityname;
        ImageView cityimage;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            popularcityname = itemView.findViewById(R.id.popularcityname);
            cityimage = itemView.findViewById(R.id.cityimage);
        }
    }
}

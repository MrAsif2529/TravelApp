package com.example.travelapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    private List<RecRecyclerViewItem> citydata;

    public RecAdapter(List<RecRecyclerViewItem> citydata) {
        this.citydata = citydata;
    }

    @NonNull
    @Override
    public RecAdapter.RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_recyclerview_item, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        RecRecyclerViewItem recRecyclerViewItem = citydata.get(position);
        holder.recCityName.setText(recRecyclerViewItem.getRecCityName());
        holder.recCityImage.setImageResource(recRecyclerViewItem.getRecCityImage());
    }

    @Override
    public int getItemCount() {
        return citydata.size();
    }


    public static class RecViewHolder extends RecyclerView.ViewHolder {
        private TextView recCityName;
        ImageView recCityImage;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            recCityName = itemView.findViewById(R.id.recCityName);
            recCityImage = itemView.findViewById(R.id.recCityImage);
        }
    }

    public void refresh(List<RecRecyclerViewItem> list){
        citydata.clear();
        this.citydata = list;
        notifyDataSetChanged();
    }
}

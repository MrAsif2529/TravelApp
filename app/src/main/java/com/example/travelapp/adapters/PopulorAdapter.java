package com.example.travelapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.model.Places;
import com.example.travelapp.model.PopularRecyclerViewItem;
import com.example.travelapp.R;

import java.util.List;

public class PopulorAdapter extends RecyclerView.Adapter<PopulorAdapter.PopularViewHolder> {
    private List<Places> data;
    public PopulorAdapter(List<Places> data) {
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
        Places popularRecyclerViewItem = data.get(position);
        holder.popularcityname.setText(popularRecyclerViewItem.getCityName());
//        holder.cityimage.setImageResource(popularRecyclerViewItem.getImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        private TextView popularcityname;
        ImageView cityimage;
        ImageView favHeart;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            popularcityname = itemView.findViewById(R.id.popularcityname);
            cityimage = itemView.findViewById(R.id.cityimage);
            favHeart = itemView.findViewById(R.id.favHeart);
        }
    }
}

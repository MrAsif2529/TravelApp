package com.example.travelapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.model.FavoritesRecyclerViewItem;
import com.example.travelapp.R;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {
    private List<FavoritesRecyclerViewItem> favdata;
    public FavoritesAdapter(List<FavoritesRecyclerViewItem> favdata) {
        this.favdata= favdata;

    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_recyclerview_item, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        FavoritesRecyclerViewItem favoritesRecyclerViewItem = favdata.get(position);
        holder.favcityname.setText(favoritesRecyclerViewItem.getFavCityName());
        holder.favcityimage.setImageResource(favoritesRecyclerViewItem.getFavCityImage());
    }

    @Override
    public int getItemCount() {
        return favdata.size();
    }


    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        private TextView favcityname;
        ImageView favcityimage;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            favcityname = itemView.findViewById(R.id.favcityname);
            favcityimage = itemView.findViewById(R.id.favcityimage);
        }
    }
}

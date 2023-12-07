package com.example.travelapp.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.travelapp.R;
import com.example.travelapp.model.Places;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {
    public List<Places> favdata;

    public FavoritesAdapter(List<Places> favdata) {
        this.favdata = favdata;

    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_recyclerview_item, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Places item = favdata.get(position);
        holder.favcityname.setText(item.getCityName());
        holder.mRating.setText(item.getRating());
//        holder.favcityimage.setImageResource(favoritesRecyclerViewItem.getImage());

        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(item.getImage())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.favcityimage.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    @Override
    public int getItemCount() {
        return favdata.size();
    }


    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        private TextView favcityname;
        private ImageView favcityimage;
        private TextView mRating;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            favcityname = itemView.findViewById(R.id.favcityname);
            favcityimage = itemView.findViewById(R.id.favcityimage);
            mRating = itemView.findViewById(R.id.favrating);
        }
    }

    public void refresh(List<Places> list) {
        favdata.clear();
        this.favdata = list;
        notifyDataSetChanged();
    }
}

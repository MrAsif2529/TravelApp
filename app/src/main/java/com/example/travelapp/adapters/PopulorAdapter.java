package com.example.travelapp.adapters;

import android.content.Intent;
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
import com.example.travelapp.activities.PlaceViewActivity;
import com.example.travelapp.model.Places;

import java.util.List;

public class PopulorAdapter extends RecyclerView.Adapter<PopulorAdapter.PopularViewHolder> {
    public List<Places> data;
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
        Places item = data.get(position);
        holder.popularcityname.setText(item.getCityName());
        holder.mRating.setText(item.getRating());
//        holder.cityimage.setImageResource(popularRecyclerViewItem.getImage());
        Glide.with(holder.itemView.getContext()).asBitmap().load(item.getImage()).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                holder.cityimage.setImageBitmap(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), PlaceViewActivity.class);
            intent.putExtra("data", item);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        private TextView popularcityname;
        ImageView cityimage;
        ImageView favHeart;

        private TextView mRating;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            popularcityname = itemView.findViewById(R.id.popularcityname);
            cityimage = itemView.findViewById(R.id.cityimage);
            favHeart = itemView.findViewById(R.id.favHeart);
            mRating = itemView.findViewById(R.id.textView7);
        }
    }

    public void refresh(List<Places> list) {
        data.clear();
        this.data = list;
        notifyDataSetChanged();
    }
}

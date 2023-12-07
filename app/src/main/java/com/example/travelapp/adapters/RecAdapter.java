package com.example.travelapp.adapters;

import static android.view.LayoutInflater.from;
import static com.example.travelapp.R.layout.custom_recommended_items;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.model.Places;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    private List<Places> mLocationsList;
    private IRecommendListener mCallBack;
    private List<String> favList = new ArrayList<>();

    public RecAdapter(List<Places> recommendLocations, IRecommendListener callBack,
                      List<String> favList) {
        this.mLocationsList = recommendLocations;
        this.mCallBack = callBack;
        this.favList = favList;
    }

    @NonNull
    @Override
    public RecAdapter.RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = from(parent.getContext()).inflate(custom_recommended_items, parent, false);
        return new RecViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        Places item = mLocationsList.get(position);
        holder.recCityName.setText(item.getCityName());

//        holder.recCityImage.setImageResource(item.getImage());
        holder.favIcon.setOnClickListener(view -> {
            if (favList.contains(item.getCityName())) favList.remove(item.getCityName());
            else favList.remove(item.getCityName());
//            mCallBack.onFave(item)
        });


    }

    @Override
    public int getItemCount() {
        return mLocationsList.size();
    }


    public static class RecViewHolder extends RecyclerView.ViewHolder {
        private final TextView recCityName;
        ImageView recCityImage;

        private final ImageView favIcon;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            recCityName = itemView.findViewById(R.id.recCityName);
            recCityImage = itemView.findViewById(R.id.recCityImage);
            favIcon = itemView.findViewById(R.id.favIcon);
        }
    }

    public void refresh(List<Places> list) {
        mLocationsList.clear();
        this.mLocationsList = list;
        notifyDataSetChanged();
    }

    public interface IRecommendListener {
        void onClick(Places item);

        void onFave(Places item);
    }
}

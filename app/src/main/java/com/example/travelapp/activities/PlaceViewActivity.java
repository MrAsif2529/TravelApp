package com.example.travelapp.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.travelapp.R;
import com.example.travelapp.database.FirebaseHelper;
import com.example.travelapp.model.Places;

public class PlaceViewActivity extends AppCompatActivity {
    private TextView cityName, cityDetails, mRating;
    private ImageView cityImage;

    private static final String TAG = "PlaceViewActivity";

    private FirebaseHelper mHelper = new FirebaseHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_view);


        cityName = findViewById(R.id.statecityName);
        mRating = findViewById(R.id.textView15);

        cityDetails = findViewById(R.id.statecityDetails);
        cityImage = findViewById(R.id.statecityImage);

        Places places = (Places) getIntent().getSerializableExtra("data");
        Log.d(TAG, "data" + places.toString());

        cityDetails.setText(places.getDescription());

        cityName.setText(places.getCityName());

        String rating = getIntent().getStringExtra(places.getRating());
        mRating.setText(rating);

        ImageView favImageView = findViewById(R.id.preview_fav_place);
        favImageView.setImageResource(places.isFav() ? R.drawable.baseline_favorite_24 : R.drawable.baseline_gray);

        favImageView.setOnClickListener(view -> {
            if (!places.isFav()) {
                mHelper.addFav(places.getCityKeys().getPlaceId());
                favImageView.setImageResource(R.drawable.baseline_favorite_24);
                places.setFav(true);
            }

        });

        Glide.with(this).

                asBitmap().

                load(places.getImage()).

                into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                cityImage.setImageBitmap(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });

    }
}
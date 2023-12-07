package com.example.travelapp.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.travelapp.R;

public class PlaceViewActivity extends AppCompatActivity {
    private TextView cityName, cityDetails, mRating;
    private ImageView cityImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_view);


        cityName = findViewById(R.id.statecityName);
        mRating = findViewById(R.id.textView15);

        cityDetails = findViewById(R.id.statecityDetails);
        cityImage = findViewById(R.id.statecityImage);


        String description = getIntent().getStringExtra("description");
        cityDetails.setText(description);

        String image = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        cityName.setText(name);

        String rating = getIntent().getStringExtra("rating");
        mRating.setText(rating);

        Glide.with(this).asBitmap().load(image).into(new CustomTarget<Bitmap>() {
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
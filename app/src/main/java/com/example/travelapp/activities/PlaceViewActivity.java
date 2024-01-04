package com.example.travelapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.travelapp.R;
import com.example.travelapp.database.FirebaseHelper;
import com.example.travelapp.fragments.FragmentBottomSheet;
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

        ImageView ratingArrow = findViewById(R.id.ratingArrow);
        ratingArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of your bottom fragment
                FragmentBottomSheet bottomFragmentSheet = new FragmentBottomSheet();

                // Get the FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager(); // For Activity, use getFragmentManager()

                // Begin the fragment transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Replace the existing fragment/container with your bottomFragment
                fragmentTransaction.replace(R.id.fragment_container, bottomFragmentSheet); // Use bottomFragmentSheet instead of FragmentBottomSheet
                fragmentTransaction.addToBackStack(null); // Add to back stack for back navigation

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        TextView showMap = findViewById(R.id.showMap);
        showMap.setClickable(true); // Ensure the TextView is clickable

        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=22.659239,88.4355534&mode=l"));
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    // Google Maps app not found, handle this case (open in browser or prompt to install Maps)
                    Toast.makeText(getApplicationContext(), "Google Maps app not found", Toast.LENGTH_SHORT).show();
                    // Alternatively, provide an option to redirect to the Play Store for Maps installation.
                    // You can create an Intent with the Play Store URL for Google Maps.
                }
            }
        });


    }
}
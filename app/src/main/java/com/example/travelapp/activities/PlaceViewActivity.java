package com.example.travelapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.travelapp.R;
import com.example.travelapp.database.FirebaseHelper;
import com.example.travelapp.fragments.FragmentBottomSheet;
import com.example.travelapp.model.Places;
import com.example.travelapp.utils.LocationHelper;

public class PlaceViewActivity extends AppCompatActivity {
    private TextView cityName, cityDetails, mRating;
    private ImageView cityImage;

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private LocationHelper locationHelper;
    private static final String TAG = "PlaceViewActivity";

    private FirebaseHelper mHelper = new FirebaseHelper();

    private double srcLatitude, srcLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_view);


        locationHelper = new LocationHelper(this);

        if (!locationHelper.areLocationPermissionsEnabled()) {
            checkLocationPermission();
        } else {
            getLocation();
        }

        cityName = findViewById(R.id.statecityName);
        mRating = findViewById(R.id.textView15);

        cityDetails = findViewById(R.id.statecityDetails);
        cityImage = findViewById(R.id.statecityImage);

        Places places = (Places) getIntent().getSerializableExtra("data");
        Log.d(TAG, "data" + places.toString());

        cityDetails.setText(places.getDescription());

        cityName.setText(places.getCityName());

        mRating.setText(places.getRating() + "");

        TextView rating_count = findViewById(R.id.rating_count);
        rating_count.setText(places.getCount() + " Reviews");

        ImageView favImageView = findViewById(R.id.preview_fav_place);
        favImageView.setImageResource(places.isFav() ? R.drawable.baseline_favorite_24 : R.drawable.baseline_gray);

        findViewById(R.id.showMap).setOnClickListener(view -> {
            redirectMap(places.getLatitude(), places.getLongitude());
        });

        favImageView.setOnClickListener(view -> {
            Log.d(TAG, "isFav " + places.isFav());

            if (!places.isFav()) {
                mHelper.addFav(places.getCityKeys().getPlaceId());
                favImageView.setImageResource(R.drawable.baseline_favorite_24);
                places.setFav(true);
            }else {
//                mHelper.addFav(places.getCityKeys().getPlaceId());
                favImageView.setImageResource(R.drawable.baseline_gray);
                places.setFav(false);
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
        ratingArrow.setOnClickListener(v -> {
            FragmentBottomSheet bottomSheetFragment = new FragmentBottomSheet();
            bottomSheetFragment.show(places.getCityKeys().getCityId(), places.getCityKeys().getPlaceId());

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.rating_fragment_container, bottomSheetFragment).commit();
            }
        });

        TextView showMap = findViewById(R.id.showMap);
        showMap.setClickable(true); // Ensure the TextView is clickable

        showMap.setOnClickListener(view -> {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=25.1972,55.2744&mode=l"));
            mapIntent.setPackage("com.google.android.apps.maps");


            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                // Google Maps app not found, handle this case (open in browser or prompt to install Maps)
                Toast.makeText(getApplicationContext(), "Google Maps app not found", Toast.LENGTH_SHORT).show();
                // Alternatively, provide an option to redirect to the Play Store for Maps installation.
                // You can create an Intent with the Play Store URL for Google Maps.
            }
        });
        ImageView backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> {
            Intent b = new Intent(PlaceViewActivity.this, MainActivity.class);
            startActivity(b);
        });

    }

    private void redirectMap(double latitude, double longitude) {
        String uri = "http://maps.google.com/maps?saddr=" + srcLatitude + "," + srcLongitude + "&daddr=" + latitude + "," + longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getLocation() {
        locationHelper.requestLocationUpdates(locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationHelper.removeLocationUpdates();
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            srcLatitude = location.getLatitude();
            srcLongitude = location.getLongitude();
            // Handle location change
            Log.d(TAG, "onLocationChanged: " + location.getLatitude() + " : " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };
}
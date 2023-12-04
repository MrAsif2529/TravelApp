package com.example.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    private ImageView home, favorite, profile;
    private  TextView explore, holiday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        home = findViewById(R.id.home);
        findViewById(R.id.home).setOnClickListener(view -> {
            showFragment(new HomeFragment());
            changeBackground(home);


        });

        showFragment(new HomeFragment());
        favorite = findViewById(R.id.favrite);
        findViewById(R.id.favrite).setOnClickListener(view -> {
            showFragment(new  FavouritesFragment());
            changeBackground(favorite);
            explore.setText("Here");


        });
//        showFragment(new FavouritesFragment());
        profile = findViewById(R.id.profile);
        findViewById(R.id.profile).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            changeBackground(profile);

        });
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void changeBackground(ImageView clickedImageView) {

        home.setImageResource(R.drawable.home_empty);
        favorite.setImageResource(R.drawable.fav_empty);
        profile.setImageResource(R.drawable.profile_empty);

        if (clickedImageView == home) {
            clickedImageView.setImageResource(R.drawable.home_fill);
        } else if (clickedImageView == favorite) {
            clickedImageView.setImageResource(R.drawable.fav_fill);
        } else if (clickedImageView == profile) {
            clickedImageView.setImageResource(R.drawable.profile_fill);
        }
    }



}
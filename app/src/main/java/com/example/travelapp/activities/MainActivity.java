package com.example.travelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.travelapp.R;
import com.example.travelapp.fragments.FavouritesFragment;
import com.example.travelapp.fragments.HomeFragment;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private ImageView home, favorite, profile;
    private TextView explore, holiday;

    FirebaseFirestore firestore;

    public EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTextSearch);

        home = findViewById(R.id.home);
        findViewById(R.id.home).setOnClickListener(view -> {
            showFragment(new HomeFragment());
            changeBackground(home);


        });

        showFragment(new HomeFragment());
        favorite = findViewById(R.id.favrite);
        findViewById(R.id.favrite).setOnClickListener(view -> {
            showFragment(new FavouritesFragment());
            changeBackground(favorite);


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

// Optionally, set up a click listener for the search button
        /*
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(view -> {
            String searchText = searchEditText.getText().toString();
            performSearch(searchText);
        });
        */


    private void performSearch(String query) {
        // Handle the search query (e.g., search in a list, database, etc.)
        // You can implement your logic here
        Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
    }



}
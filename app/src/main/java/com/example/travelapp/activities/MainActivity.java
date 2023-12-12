package com.example.travelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.travelapp.fragments.FavouritesFragment;
import com.example.travelapp.fragments.HomeFragment;
import com.example.travelapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ImageView home, favorite, profile;
    private TextView explore, holiday;
    private EditText editTextSearch;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextSearch = findViewById(R.id.editTextSearch);

        // Set up a listener for the search action
        editTextSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(textView.getText().toString());
                return true;
            }
            return false;
        });




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
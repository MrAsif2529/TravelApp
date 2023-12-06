package com.example.travelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextSearch = findViewById(R.id.editTextSearch);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();
                // Implement your search logic here

                // For example, if you have a list of destinations to filter
                List<String> filteredDestinations = new ArrayList<>();
                for (String destination : filteredDestinations ) {
                    if (destination.toLowerCase().contains(searchText.toLowerCase())) {
                        filteredDestinations.add(destination);
                    }
                }

                // Update your UI or perform actions based on the filtered results
                // For instance, display the filtered results in a RecyclerView or ListView
                // adapter.updateData(filteredDestinations);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for search functionality
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed for search functionality
            }
        });

        firestore= FirebaseFirestore.getInstance();

        Map<String,Object> users = new HashMap<>();
        users.put("firstName","EASY");
        users.put("lastName","TUTO");
        users.put("description","Subscribe");

        firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG).show();
            }
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





}
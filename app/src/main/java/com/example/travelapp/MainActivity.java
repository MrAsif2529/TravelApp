package com.example.travelapp;

import static com.example.travelapp.Constants.ABUDABI;
import static com.example.travelapp.Constants.AJMAN;
import static com.example.travelapp.Constants.DUBAI;
import static com.example.travelapp.Constants.FAJOURIA;
import static com.example.travelapp.Constants.RASUL_ALHAIMA;
import static com.example.travelapp.Constants.SHARJAH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.home).setOnClickListener(view -> {
            showFragment(new HomeFragment());
        });

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
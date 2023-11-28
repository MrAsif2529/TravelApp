package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceViewActivity extends AppCompatActivity {
    private TextView cityName, cityDetails;
    private ImageView cityImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_view);


        cityName = findViewById(R.id.statecityName);
        cityDetails = findViewById(R.id.statecityDetails);
        cityImage = findViewById(R.id.statecityImage);

        cityImage.setImageResource(getIntent().getIntExtra("cityimage", Integer.parseInt("0")));
        cityName.setText(getIntent().getStringExtra("cityname"));
        cityDetails.setText(getIntent().getStringExtra("citydetails"));

    }
}
package com.example.travelapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.travelapp.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashScreenActivity.this, StartingActivity.class);
            startActivity(mainIntent);
            finish();
        }, SPLASH_TIMEOUT);

    }
}

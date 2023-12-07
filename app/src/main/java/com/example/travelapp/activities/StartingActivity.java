package com.example.travelapp.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;
import com.example.travelapp.database.FirebaseHelper;

public class StartingActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);


        TextView textView = (TextView) findViewById(R.id.textView10);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        findViewById(R.id.textView12).setOnClickListener(view -> {
            if (FirebaseHelper.isNewUser())
                startActivity(new Intent(StartingActivity.this, SignInActivity.class));
            else
                startActivity(new Intent(StartingActivity.this, MainActivity.class));
        });


    }
}
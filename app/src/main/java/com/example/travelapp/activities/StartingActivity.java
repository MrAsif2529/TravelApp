package com.example.travelapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.travelapp.R;
import com.google.firebase.database.FirebaseDatabase;

public class StartingActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);


        TextView textView = (TextView) findViewById(R.id.textView10);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        findViewById(R.id.textView12).setOnClickListener(view -> {
            startActivity(new Intent(StartingActivity.this, MainActivity.class));
        });


    }
}
package com.example.travelapp;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {

    private Button signUpBtn;
    private EditText emailText;
    private EditText passwordText;
    private Button signInBtn;
    private static final String TAG = "LoginActivity";
    private TextView createBtn;
    private EditText secondPass;
    private EditText confirm;
    private  EditText fullname;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        fullname = findViewById(R.id.editText4);
        fullname.setVisibility(View.INVISIBLE);
        confirm = findViewById(R.id.editText3);
        confirm.setVisibility(View.INVISIBLE);
        emailText = findViewById(R.id.editText2);
        passwordText = findViewById(R.id.editText);
        secondPass = findViewById(R.id.editText3);
        signUpBtn = findViewById(R.id.SignUpBtn);
        signInBtn = findViewById(R.id.LoginBtn);
        createBtn = findViewById(R.id.CreateAccount);
        signUpBtn.setVisibility(View.GONE);


        createBtn.setOnClickListener(v -> {
            emailText.setText("");
            passwordText.setText("");
            if (createBtn.getText().toString().equals("SIGN UP")) {
                fullname.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signInBtn.setVisibility(View.INVISIBLE);
                createBtn.setText("Back to Sign In");
            } else {
                confirm.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.INVISIBLE);
                signInBtn.setVisibility(View.VISIBLE);
                fullname.setVisibility(View.INVISIBLE);
                createBtn.setText("SIGN UP");

            }
        });
        signUpBtn.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI accordingly
                            FirebaseUser user = mAuth.getCurrentUser();
                            // You can add more actions upon successful sign up if needed
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Set click listener for Sign In
        signInBtn.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI accordingly
                            FirebaseUser user = mAuth.getCurrentUser();
                            // You can add more actions upon successful sign in if needed
                            moveToMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        }

    private void moveToMainActivity() {
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the SignInActivity so users can't go back using the back button
    }
}
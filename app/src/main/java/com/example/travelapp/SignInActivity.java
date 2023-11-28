package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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


    }
}
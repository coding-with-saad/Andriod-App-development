package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText etSignupEmail, etSignupFullName, etSignupUsername, etSignupPassword;
    Button btnSignup, btnGoogleSignup, btnFacebookSignup;
    TextView tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupFullName = findViewById(R.id.etSignupFullName);
        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnGoogleSignup = findViewById(R.id.btnGoogleSignup);
        btnFacebookSignup = findViewById(R.id.btnFacebookSignup);
        tvGoLogin = findViewById(R.id.tvGoLogin);

        btnSignup.setOnClickListener(v -> {
            // TODO: add signup logic here
        });

        btnGoogleSignup.setOnClickListener(v -> {
            // TODO: add Google signup here
        });

        btnFacebookSignup.setOnClickListener(v -> {
            // TODO: add Facebook signup here
        });

        tvGoLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }
}
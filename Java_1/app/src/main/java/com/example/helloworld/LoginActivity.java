package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginUser, etLoginPassword;
    Button btnLogin, btnGoogleLogin, btnFacebookLogin;
    TextView tvGoSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginUser = findViewById(R.id.etLoginUser);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin);
        btnFacebookLogin = findViewById(R.id.btnFacebookLogin);
        tvGoSignup = findViewById(R.id.tvGoSignup);

        btnLogin.setOnClickListener(v -> {
            // TODO: add login logic here
        });

        btnGoogleLogin.setOnClickListener(v -> {
            // TODO: add Google login here
        });

        btnFacebookLogin.setOnClickListener(v -> {
            // TODO: add Facebook login here
        });

        tvGoSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        });
    }
}
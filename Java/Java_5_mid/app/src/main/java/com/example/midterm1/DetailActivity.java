
package com.example.midterm1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.TextView;

public class DetailActivity extends Activity {

    TextView tvName, tvEmail, tvAddress;
    TextView tvPassword, tvAgreement;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);
        tvPassword = findViewById(R.id.tvPassword);
        tvAgreement = findViewById(R.id.tvAgreement);

        // Get saved data
        SharedPreferences preferences =
                getSharedPreferences("UserData", MODE_PRIVATE);

        String name = preferences.getString("name", "");
        String email = preferences.getString("email", "");
        String address = preferences.getString("address", "");
        String password = preferences.getString("password", "");

        boolean agree = preferences.getBoolean("agree", false);

        // Display information
        tvName.setText("Username: " + name);
        tvEmail.setText("Email: " + email);
        tvAddress.setText("Address: " + address);
        tvPassword.setText("Password: " + password);
        tvAgreement.setText("Agreement: " + agree);
    }
}

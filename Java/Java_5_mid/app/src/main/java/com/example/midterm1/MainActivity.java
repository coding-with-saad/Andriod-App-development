
package com.example.midterm1;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText etName, etEmail, etAddress, etPassword;
    CheckBox checkBox;
    Button btnWelcome, btnSubmit;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect XML views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
        checkBox = findViewById(R.id.checkBox);

        btnWelcome = findViewById(R.id.btnWelcome);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Shared Preferences
        preferences = getSharedPreferences(
                "UserData", MODE_PRIVATE);

        // Load previously saved data
        etName.setText(preferences.getString("name", ""));
        etEmail.setText(preferences.getString("email", ""));
        etAddress.setText(preferences.getString("address", ""));
        etPassword.setText(preferences.getString("password", ""));
        checkBox.setChecked(
                preferences.getBoolean("agree", false));

        // Welcome Button
        btnWelcome.setOnClickListener(v -> {
            Toast.makeText(
                    MainActivity.this,
                    "Welcome to Student App!",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Submit Button
        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String password = etPassword.getText().toString();
            boolean agree = checkBox.isChecked();

            if (name.isEmpty() || email.isEmpty()
                    || address.isEmpty() || password.isEmpty()) {

                Toast.makeText(this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email).matches()) {
                etEmail.setError("Enter valid email");
                return;
            }

            if (!agree) {
                Toast.makeText(this,
                        "Please accept terms and conditions",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Save data in SharedPreferences
            SharedPreferences.Editor editor =
                    preferences.edit();

            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("address", address);
            editor.putString("password", password);
            editor.putBoolean("agree", agree);

            editor.apply();

            Toast.makeText(this,
                    "Data Saved Successfully",
                    Toast.LENGTH_SHORT).show();

            // Move to Detail Activity
            Intent intent = new Intent(
                    MainActivity.this,
                    DetailActivity.class);

            startActivity(intent);
        });
    }
}

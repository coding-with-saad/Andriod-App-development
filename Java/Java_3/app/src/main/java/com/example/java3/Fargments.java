
package com.example.java3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class Fargments extends AppCompatActivity {

    Button btnnews;
    Button btnnotice;
    Button btnprofile;


    Button intent,intent2,intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fargements);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // Connect Java buttons with XML buttons
        btnnews = findViewById(R.id.btnnews);
        btnnotice = findViewById(R.id.btnnotice);
        btnprofile = findViewById(R.id.btnprofile);
        intent = findViewById(R.id.intent);
        intent2 = findViewById(R.id.intent2);
        intent3 = findViewById(R.id.intent3);

        // News button
        btnnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager =
                        getSupportFragmentManager();

                manager.beginTransaction()
                        .replace(R.id.fragmentContainerView,
                                NewsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("news")
                        .commit();
            }
        });

        // Notice button
        btnnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager =
                        getSupportFragmentManager();

                manager.beginTransaction()
                        .replace(R.id.fragmentContainerView,
                                NoticeFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("notice")
                        .commit();
            }
        });

        // Profile button
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager =
                        getSupportFragmentManager();

                manager.beginTransaction()
                        .replace(R.id.fragmentContainerView,
                                ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("profile")
                        .commit();
            }
        });


        intent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Fargments.this,MainActivity2.class);

                startActivity(i);
            }
        });
        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Fargments.this,Fuel_Estimate_App.class);
                startActivity(j);
            }
        });

        intent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Fargments.this,MainActivity.class);
                startActivity(k);
            }
        });
    }
}

package com.example.java3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Fuel_Estimate_App extends AppCompatActivity {

    // create object
    Button btncost;
    EditText txtdist;
    EditText txteffc,txtfuel;
    TextView price;

    Button intent3,intent2,intent4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fuel_estimate_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // bind ui to java component

        btncost=findViewById(R.id.btncost);
        price=findViewById(R.id.price);  //show final result

        txtdist=findViewById(R.id.txtdist);
        txteffc=findViewById(R.id.txteffc);
        txtfuel=findViewById(R.id.txtfuel);
        intent3=findViewById(R.id.intent3);
        intent2=findViewById(R.id.intent2);
        intent4=findViewById(R.id.intent4);

        btncost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dist=txtdist.getText().toString();
                String eff =txteffc.getText().toString();
                String fuel=txtfuel.getText().toString();


                double d = Double.parseDouble(dist);
                double e = Double.parseDouble(eff);
                double f = Double.parseDouble(fuel);

                // Total fuel cost
                double ans = (d / e) * f;

                price.setText(String.format(java.util.Locale.US, "%.2f PKR", ans));

            }
        });



        intent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Fuel_Estimate_App.this,MainActivity.class);

                startActivity(i);
            }
        });
        intent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Fuel_Estimate_App.this,MainActivity2.class);
                startActivity(j);
            }
        });

        intent4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Fuel_Estimate_App.this,Fargments.class);
                startActivity(k);
            }
        });

    }
}
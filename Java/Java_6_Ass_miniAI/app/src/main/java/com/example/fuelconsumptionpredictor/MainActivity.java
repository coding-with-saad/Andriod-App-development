
package com.example.fuelconsumptionpredictor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etEngineSize;
    private EditText etCylinders;
    private EditText etHorsepower;
    private EditText etWeight;

    private Button btnPredict;
    private TextView tvResult;

    private Interpreter interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEngineSize = findViewById(R.id.etEngineSize);
        etCylinders = findViewById(R.id.etCylinders);
        etHorsepower = findViewById(R.id.etHorsepower);
        etWeight = findViewById(R.id.etWeight);

        btnPredict = findViewById(R.id.btnPredict);
        tvResult = findViewById(R.id.tvResult);

        TextView btnAbout = findViewById(R.id.btnAbout);

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AboutActivity.class
            );
            startActivity(intent);
        });

        try {
            interpreter = new Interpreter(loadModelFile());
        } catch (IOException | RuntimeException e) {
            Toast.makeText(
                    this,
                    "Unable to load ML model",
                    Toast.LENGTH_LONG
            ).show();

            btnPredict.setEnabled(false);
        }

        btnPredict.setOnClickListener(v -> predictFuelEfficiency());
    }

    private ByteBuffer loadModelFile() throws IOException {

        FileInputStream inputStream = new FileInputStream(
                getAssets().openFd("fuel_consumption.tflite").getFileDescriptor()
        );

        FileChannel fileChannel = inputStream.getChannel();

        long startOffset = getAssets()
                .openFd("fuel_consumption.tflite")
                .getStartOffset();

        long declaredLength = getAssets()
                .openFd("fuel_consumption.tflite")
                .getLength();

        ByteBuffer modelBuffer = fileChannel.map(
                FileChannel.MapMode.READ_ONLY,
                startOffset,
                declaredLength
        );

        inputStream.close();

        return modelBuffer;
    }

    private void predictFuelEfficiency() {

        if (interpreter == null) {
            Toast.makeText(
                    this,
                    "ML model is not available",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        try {
            float displacement = Float.parseFloat(
                    etEngineSize.getText().toString().trim()
            );

            float cylinders = Float.parseFloat(
                    etCylinders.getText().toString().trim()
            );

            float horsepower = Float.parseFloat(
                    etHorsepower.getText().toString().trim()
            );

            float weight = Float.parseFloat(
                    etWeight.getText().toString().trim()
            );

            if (displacement <= 0 || cylinders <= 0
                    || horsepower <= 0 || weight <= 0) {

                Toast.makeText(
                        this,
                        "Please enter values greater than zero",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            float[][] input = {{
                    displacement,
                    cylinders,
                    horsepower,
                    weight
            }};

            float[][] output = new float[1][1];

            interpreter.run(input, output);

            float predictedMPG = output[0][0];

            tvResult.setText(
                    String.format(
                            Locale.US,
                            "%.2f MPG",
                            predictedMPG
                    )
            );

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Please fill in all vehicle details correctly",
                    Toast.LENGTH_SHORT
            ).show();

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Prediction failed. Please try again.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (interpreter != null) {
            interpreter.close();
        }
    }
}

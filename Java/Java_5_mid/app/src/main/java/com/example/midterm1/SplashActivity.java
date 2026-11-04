
package com.example.midterm1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends Activity {

    private final Handler handler =
            new Handler(Looper.getMainLooper());

    private final Runnable splashRunnable = () -> {
        startActivity(new Intent(
                SplashActivity.this,
                MainActivity.class
        ));
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        handler.postDelayed(splashRunnable, 3000);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(splashRunnable);
        super.onDestroy();
    }
}

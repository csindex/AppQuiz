package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AppCompatButton btnStart = findViewById(R.id.btn_start);
        RelativeLayout loadingLayout = findViewById(R.id.layout_loading);
        Handler handler = new Handler();
        btnStart.setOnClickListener(view -> {
            runOnUiThread(() -> {
                btnStart.setVisibility(View.GONE);
                loadingLayout.setVisibility(View.VISIBLE);
                handler.postDelayed(() -> {
                    Intent i = new Intent(this, MainActivity2.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }, 1500);
            });
        });
    }

}
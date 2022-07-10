package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TextView labelWelcome = findViewById(R.id.label_welcome);
        Shader shader = new LinearGradient(0,0,0,labelWelcome.getLineHeight(),
                getResources().getColor(R.color.baby_squid_red), getResources().getColor(R.color.baby_squid_red_gradient_end),
                Shader.TileMode.REPEAT);
        labelWelcome.getPaint().setShader(shader);
        labelWelcome.setTextColor(getResources().getColor(R.color.baby_squid_red));
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
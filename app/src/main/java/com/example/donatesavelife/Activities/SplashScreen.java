package com.example.donatesavelife.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.donatesavelife.R;
//import com.example.donateandsavelife.R;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    ImageView img;
    TextView logo;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        img = findViewById(R.id.img);
        logo = findViewById(R.id.logo);
        lottieAnimationView = findViewById(R.id.lottie);

        img.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override

            public void run() {
                Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCREEN);


    }



}
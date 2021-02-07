package com.example.donatesavelife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.donatesavelife.R;

public class Options extends AppCompatActivity {

    Button ngo , donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ngo = findViewById(R.id.ngo);
        donate = findViewById(R.id.donate);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Options.this,MainActivity.class));


            }
        });


        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Options.this,NgoHomepageActivity.class));


            }
        });




    }
}
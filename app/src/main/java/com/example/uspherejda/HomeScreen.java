package com.example.uspherejda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        //Set up a custom bar using a custom
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_bar_image, null);
        actionBar.setCustomView(view);

        //Image Buttons.
        ImageButton btnSat = (ImageButton) findViewById(R.id.btnSatellite);
        //Texts.
        TextView lblSat = findViewById(R.id.lblSatellite);

        btnSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load list of satellites
            }
        });
    }
}
package com.example.uspherejda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.uspherejda.DB.SatFromHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {
    //SQL
    private SatFromHelper dbHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        dbHelper = new SatFromHelper(this);
        db = dbHelper.getWritableDatabase();
        //dbHelper.createTable(db);

        //call the Home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
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
        //Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.nav_menu);
        //Item listener when one of hte items below from the nav_bar is selected
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch(item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_list:
                    selectedFragment = new ListFragment(dbHelper, db);
                    break;
                case R.id.nav_add:
                    selectedFragment = new AddFragment(dbHelper, db);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        /*Click listener when the image is pressed
        btnSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load list of satellites
            }
        }); */
    }
}
package com.example.uspherejda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.uspherejda.DB.SatFromHelper;
import com.example.uspherejda.Fragments.AddFragment;
import com.example.uspherejda.Fragments.HomeFragment;
import com.example.uspherejda.Fragments.ListFragment;
import com.example.uspherejda.Fragments.SettingsFragment;
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
        //call the Home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        //Set up a custom bar using a custom
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.start_dark_blue)));
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_bar_image, null);
        actionBar.setCustomView(view);

        //Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.nav_menu);
        //Item listener when one of hte items below from the nav_bar is selected
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch(item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment(dbHelper, db);
                    break;
                case R.id.nav_list:
                    selectedFragment = new ListFragment(dbHelper, db);
                    break;
                case R.id.nav_add:
                    selectedFragment = new AddFragment(dbHelper, db);
                    break;
                case R.id.nav_settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_out_right,  R.anim.slide_in_left ).replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });
    }
    //Close the db when the activity onDestroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        dbHelper.close();
        db.close();
    }
    //when exit activity do the following animation
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
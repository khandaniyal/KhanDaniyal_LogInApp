package com.example.uspherejda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons.
        Button btnLogin = findViewById(R.id.btnLogin);
        //Texts.
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);
        //Images.
        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        ImageView imgTitle = (ImageView) findViewById(R.id.imgTitle);
        ImageView imgCheck = (ImageView) findViewById(R.id.imgCheckerino);
        //Moves the elements in the Y axis downwards.
        btnLogin.setTranslationY(150);
        txtUsername.setTranslationY(150);
        txtPassword.setTranslationY(150);
        imgLogo.setTranslationY(150);
        imgTitle.setTranslationY(150);
        lblLoginResult.setTranslationY(150);
        //Moves the elements in the Y axis upwards, it creates a slight animation.
        btnLogin.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        txtUsername.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        txtPassword.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        imgLogo.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        imgTitle.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        lblLoginResult.animate().alpha(1f).translationYBy(-150).setDuration(1500);

        //An event is done when the Log In button is pressed.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUsername.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")){
                    lblLoginResult.setText("Logged In Successfully");
                    //If the credentials are correct set the visibity of this icon to true. In the xml file i have set the visibility to false.
                    imgCheck.setVisibility(View.VISIBLE);
                    Log.i("Test", "Login successfull");
                }else{  Log.i("Test", "Login unsuccessfull"); }
            }
        });
    }

}
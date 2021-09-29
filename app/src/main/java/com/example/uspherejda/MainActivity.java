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

        //Buttons
        Button btnLogin = findViewById(R.id.btnLogin);
        //Texts
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);
        //Images
        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        ImageView imgTitle = (ImageView) findViewById(R.id.imgTitle);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUsername.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")){
                    lblLoginResult.setText("Logged In Successfully");
                    Log.i("Test", "Login successfull");
                }else{
                    Log.i("Test", "Login unsuccessfull");
                }
            }
        });
    }
}
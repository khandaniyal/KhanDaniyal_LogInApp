package com.example.uspherejda;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import androidx.biometric.BiometricPrompt;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Executor;

public class LogIn extends AppCompatActivity {
    public static EditText txtUsername;
    public static EditText txtPassword;
    public static SharedPreferences prefs;
    private Executor executor;
    private BiometricPrompt bioPrompt;
    private BiometricPrompt.PromptInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //splash screen theme
        SystemClock.sleep(200);
        setTheme(R.style.SplashTheme);
        //main theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Top Bar custom image
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.start_dark_blue)));
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_bar_image, null);
        actionBar.setCustomView(view);
        //Buttons.
        Button btnLogin = findViewById(R.id.btnLogin);
        //Texts.
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);
        //Images.
        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        ImageView imgTitle = (ImageView) findViewById(R.id.imgTitle);
        ImageView imgCheck = (ImageView) findViewById(R.id.imgCheckerino);
        ImageView imgError = (ImageView) findViewById(R.id.imgError);
        //remember me check
        CheckBox rememberMe = (CheckBox) findViewById(R.id.boxRememberMe);
        //Animation for our logo
        Animation logoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anim);
        logoAnim.setStartOffset(1900);
        logoAnim.setRepeatCount(0);
        imgLogo.startAnimation(logoAnim);
        //Moves the elements in the Y axis downwards.
        btnLogin.setTranslationY(150);
        txtUsername.setTranslationY(150);
        txtPassword.setTranslationY(150);
        imgLogo.setTranslationY(150);
        imgTitle.setTranslationY(150);
        lblLoginResult.setTranslationY(150);
        rememberMe.setTranslationY(150);
        //Moves the elements in the Y axis upwards, it creates a slight animation.
        btnLogin.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        txtUsername.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        txtPassword.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        imgLogo.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        imgTitle.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        lblLoginResult.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        rememberMe.animate().alpha(1f).translationYBy(-150).setDuration(1500);
        //shared preferences
        prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor loginEditor = prefs.edit();
        boolean saveLogin = prefs.getBoolean("saveLogin", false);
        if(saveLogin){
            rememberMe.setChecked(true);
            startActivity(new Intent(this, HomeScreen.class));
        }
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(LogIn.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                if(saveLogin){
                    rememberMe.setChecked(true);
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                }
            }
            @Override
            public void onAuthenticationFailed() { super.onAuthenticationFailed(); }
        });
        info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.finger_title))
                .setNegativeButtonText(getString(R.string.finger_cancel))
                .build();
        biometricPrompt.authenticate(info);
        //An event is done when the Log In button is pressed.
        btnLogin.setOnClickListener(e -> {
                if(txtUsername.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")){
                    lblLoginResult.setText(getString(R.string.loginstatus1));
                    //If the credentials are correct set the visibity of this icon to true. In the xml file i have set the visibility to false.
                    imgCheck.setVisibility(View.VISIBLE);
                    imgError.setVisibility(View.INVISIBLE);
                    if(rememberMe.isChecked()){
                        loginEditor.putBoolean("saveLogin", true);
                        loginEditor.putString("username", txtUsername.getText().toString());
                        loginEditor.putString("password", txtPassword.getText().toString());
                        loginEditor.commit();
                    }else{ loginEditor.clear().commit(); }
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    //Change to Home Screen
                    Log.i("Test", "Login successfull");
                }else{
                    lblLoginResult.setText(getString(R.string.log_error));
                    //If the credentials are correct set the visibity of this icon to true. In the xml file i have set the visibility to false.
                    imgError.setVisibility(View.VISIBLE);
                    imgCheck.setVisibility(View.INVISIBLE);
                    Log.i("Test", "Login unsuccessfull");
                }
        });

    }
}
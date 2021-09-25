package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        final FirebaseUser user= fAuth.getCurrentUser();


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(user!=null)
                {

                    Intent intent1 = new Intent(SplashActivity.this, UserAccount.class);
                    startActivity(intent1);
                }
                else
                {
                    Intent intent2 = new Intent(SplashActivity.this, Login.class);
                    startActivity(intent2);
                }

            }
        },1500);
        //1.5 sec
    }
}
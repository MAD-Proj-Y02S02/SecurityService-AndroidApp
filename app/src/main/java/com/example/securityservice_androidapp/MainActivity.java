package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClicking(View view){

        int id = view.getId();

        switch(id)
        {
            case R.id.signin_btn:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
            case R.id.sites_btn:
                Intent intent2 = new Intent(this, Addsites.class);
                startActivity(intent2);
                break;
            case R.id.invoicePage:
                Intent intent3 = new Intent(this, RevenueDash.class);
                startActivity(intent3);
                break;
            case R.id.viewSec_btn:
                Intent intent4 = new Intent(this, AdminViewAllGuards.class);
                startActivity(intent4);


        }


    }

}
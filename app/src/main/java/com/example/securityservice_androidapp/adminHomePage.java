package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
    }

    public void onClicking(View view){

        int id = view.getId();

        switch(id)
        {
            case R.id.btnMngRevenue:
                Intent intent = new Intent(this, RevenueDash.class);
                startActivity(intent);
                break;

        }


    }


}
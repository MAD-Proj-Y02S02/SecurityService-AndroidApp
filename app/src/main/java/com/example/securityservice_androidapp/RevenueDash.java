package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RevenueDash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_dash);



    }

    public void onClicking(View view){

        int id = view.getId();

        switch(id)
        {
            case R.id.btnIssueInvoice:
                Intent intent = new Intent(this, issueInvoices.class);
                startActivity(intent);
                break;
               case R.id.btnViewInvoice:
               Intent intent2 = new Intent(this, ViewInvoice.class);
                startActivity(intent2);
                break;
        }


    }
}
package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addsites extends AppCompatActivity  implements View.OnClickListener{

    EditText siteName, siteOwner, siteAddress, siteNumber;
    Button addBtn;

    Sites obSite;

    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsites);

        siteName = findViewById(R.id.name_site);
        siteOwner = findViewById(R.id.owner_site);
        siteNumber = findViewById(R.id.number_site);
        siteAddress = findViewById(R.id.address_site);

        addBtn = findViewById(R.id.add_btn);


        obSite = new Sites();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id)
        {
            case R.id.add_btn:
                Add();
                break;
        }
    }


    public void Add ( ){
        db = FirebaseDatabase.getInstance("https://securityserviceapp-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Sites");

        try {
            if(TextUtils.isEmpty(siteName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(), "Please enter the site name", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty((siteOwner.getText().toString().trim()))) {
                Toast.makeText(getApplicationContext(), "Please enter the owner name", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty((siteNumber.getText().toString().trim()))) {
                Toast.makeText(getApplicationContext(), "Please enter the site Number", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty((siteAddress.getText().toString().trim()))) {
                Toast.makeText(getApplicationContext(), "Please enter the site Address", Toast.LENGTH_LONG).show();
            } else {
                obSite.setSiteName(siteName.getText().toString().trim());
                obSite.setSiteOwner(siteOwner.getText().toString().trim());
                obSite.setSiteNumber(siteNumber.getText().toString().trim());
                obSite.setSiteAddress(siteAddress.getText().toString().trim());


                db.push().setValue(obSite);
                Toast.makeText(getApplicationContext(), "sucessfully inserted", Toast.LENGTH_LONG).show();

            }

        } catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Number format exception", Toast.LENGTH_LONG) .show();
        }

        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"exception", Toast.LENGTH_LONG) .show();
        }

    }


}
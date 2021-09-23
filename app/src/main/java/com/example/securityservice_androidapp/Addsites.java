package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addsites extends AppCompatActivity  implements View.OnClickListener{

    EditText txtName, txtOwner, txtAddress, txtNumber;
    Button addBtn;

    Sites obSite;

    DatabaseReference db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsites);

        txtName = findViewById(R.id.name_site);
        txtOwner = findViewById(R.id.owner_site);
        txtNumber = findViewById(R.id.number_site);
        txtAddress = findViewById(R.id.address_site);

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
        db = FirebaseDatabase.getInstance().getReference().child("Sites");

        try {
            if(TextUtils.isEmpty(txtName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(), "Please enter the site name", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty((txtOwner.getText().toString().trim()))) {
                Toast.makeText(getApplicationContext(), "Please enter the owner name", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty((txtNumber.getText().toString().trim()))) {
                Toast.makeText(getApplicationContext(), "Please enter the site Number", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty((txtAddress.getText().toString().trim()))) {
                Toast.makeText(getApplicationContext(), "Please enter the site Address", Toast.LENGTH_LONG).show();
            } else {
                obSite.setSiteName(txtName.getText().toString().trim());
                obSite.setSiteOwner(txtOwner.getText().toString().trim());
                obSite.setSiteNumber(txtNumber.getText().toString().trim());
                obSite.getSiteAddress(txtAddress.getText().toString().trim());

                db.push().setValue(obSite);
                Toast.makeText(getApplicationContext(), "sucessfully inserted", Toast.LENGTH_LONG).show();

            }

        } catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Number format exception", Toast.LENGTH_LONG) .show();
        }

    }


}
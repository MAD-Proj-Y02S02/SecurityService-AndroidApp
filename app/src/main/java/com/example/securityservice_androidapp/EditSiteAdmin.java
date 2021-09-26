package com.example.securityservice_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditSiteAdmin extends AppCompatActivity {


    private Button saveProfileInfo;
    EditText editSname, editOwner, editAddress,editPhone;
    private Sites site;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_site_admin);


        Sites site = (Sites) getIntent().getSerializableExtra("site");


        saveProfileInfo = findViewById(R.id.saveProfileInfo_btn);

        editSname = findViewById(R.id.editFname_et);
        editOwner = findViewById(R.id.editSname_et);
        editAddress = findViewById(R.id.editOwner_et);
        editPhone = findViewById(R.id.editPhone_et);


        editSname.setText(site.getSiteName());
        editAddress.setText(site.getSiteAddress());
        editOwner.setText(site.getSiteOwner());
        editPhone.setText(site.getSiteNumber());


        saveProfileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance("https://securityserviceapp-default-rtdb.asia-southeast1.firebasedatabase.app");
                DatabaseReference rootReference =firebaseDatabase.getReference("Sites");
                DatabaseReference particularNoteReference= rootReference.child(site.getSiteID());

                particularNoteReference.child("siteName").setValue(editSname.getText().toString());
                particularNoteReference.child("siteAddress").setValue(editAddress.getText().toString());
                particularNoteReference.child("siteOwner").setValue(editOwner.getText().toString());

                particularNoteReference.child("siteNumber").setValue(editPhone.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(EditSiteAdmin.this, " updated successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),adminHomePage.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(EditSiteAdmin.this, "Some error occurred!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }});
    }
}
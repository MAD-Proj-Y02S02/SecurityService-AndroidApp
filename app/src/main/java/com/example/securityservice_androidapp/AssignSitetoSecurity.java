package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AssignSitetoSecurity extends AppCompatActivity {
    TextView textViewSite_txt;
    private Sites site;
    FirebaseFirestore fStore;
    private Button buttonAssignSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_siteto_security);

        Sites site = (Sites) getIntent().getSerializableExtra("site");



        textViewSite_txt = findViewById(R.id.textViewSite_txt);
        textViewSite_txt.setText("Assign this security guard to "+ site.getSiteName());

//        textViewSite_txt2 = findViewById(R.id.textViewSite_txt2);
//        textViewSite_txt2.setText(site.getSecurityID());

        buttonAssignSite = findViewById(R.id.buttonAssignSite);

        fStore = FirebaseFirestore.getInstance();

        buttonAssignSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    DocumentReference docRef = fStore.collection("Security").document(site.getSecurityID());
                    Map<String,Object> edited = new HashMap<>();
                    edited.put("currentSite",site.getSiteName().toString());

                    docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AssignSitetoSecurity.this, "Site Assigned", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),adminHomePage.class));
                            finish();
                        }
                    });

            }
        });

    }
}
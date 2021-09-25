package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ViewCurrentJob extends AppCompatActivity {
    private TextView myprofileText , myprofileTextdetails;
    Button offduty_btn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_current_job);

        myprofileText = findViewById(R.id.myprofile_txt);
        myprofileTextdetails = findViewById(R.id.myprofile_txt2);
        offduty_btn= findViewById(R.id.offduty_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("Security").document(user.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                myprofileText.setText("Hello " + documentSnapshot.getString("fName")+" !");

                if(documentSnapshot.getString("currentSite").equals("empty")){
                    myprofileTextdetails.setText("You are currently not assigned to a site");
                }else{
                    myprofileTextdetails.setText("You are currently assigned to "+documentSnapshot.getString("currentSite"));
                }

            }
        });

        offduty_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference docRef = fStore.collection("Security").document(user.getUid());
                Map<String,Object> edited = new HashMap<>();
                edited.put("currentSite","empty");

                docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewCurrentJob.this, "You are now off duty!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),UserAccount.class));
                        finish();
                    }
                });
            }
        });

    }
}
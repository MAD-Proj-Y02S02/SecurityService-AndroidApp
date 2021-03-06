package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    private Button saveProfileInfo;
    EditText editFname, editSname, editNIC,editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveProfileInfo = findViewById(R.id.saveProfileInfo_btn);
        editFname = findViewById(R.id.editFname_et);
        editSname = findViewById(R.id.editSname_et);
        editNIC = findViewById(R.id.editNIC_et);
        editPhone = findViewById(R.id.editPhone_et);



        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("Security").document(user.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                //myprofileText.setText("Hello " + documentSnapshot.getString("fName")+" ! \uD83D\uDC4B");
                editFname.setText( documentSnapshot.getString("fName"));
                editSname.setText(documentSnapshot.getString("lName"));
               // profileEmail.setText(documentSnapshot.getString("email"));
                editPhone.setText(documentSnapshot.getString("mobile"));
                editNIC.setText(documentSnapshot.getString("nic"));
            }
        });

        saveProfileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editFname.getText().toString().isEmpty() || editSname.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty() || editNIC.getText().toString().isEmpty()){
                    Toast.makeText(EditProfile.this, "One or Many fields are empty ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    DocumentReference docRef = fStore.collection("Security").document(user.getUid());
                    Map<String,Object> edited = new HashMap<>();
                    edited.put("fName",editFname.getText().toString());
                    edited.put("lName",editSname.getText().toString());
                    edited.put("mobile",editPhone.getText().toString());
                    edited.put("nic",editNIC.getText().toString());

                    docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MyProfile.class));
                            finish();
                        }
                    });
                }




            }
        });


    }
}
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

public class AdminUpdateSecurity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    private Button saveProfileInfo;
    EditText editFname, editSname, editNIC,editPhone;
    private Security security;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_security);

        Security security = (Security) getIntent().getSerializableExtra("security");
        fStore = FirebaseFirestore.getInstance();

        saveProfileInfo = findViewById(R.id.saveProfileInfo_btn);

        editFname = findViewById(R.id.editFname_et);
        editSname = findViewById(R.id.editSname_et);
        editNIC = findViewById(R.id.editNIC_et);
        editPhone = findViewById(R.id.editPhone_et);


        editFname.setText(security.getfName());
        editSname.setText(security.getlName());
        editNIC.setText(security.getNIC());
        editPhone.setText(security.getMobile());


        saveProfileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editFname.getText().toString().isEmpty() || editSname.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty() || editNIC.getText().toString().isEmpty()){
                    Toast.makeText(AdminUpdateSecurity.this, "One or Many fields are empty ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    DocumentReference docRef = fStore.collection("Security").document(security.getID());
                    Map<String,Object> edited = new HashMap<>();
                    edited.put("fName",editFname.getText().toString());
                    edited.put("lName",editSname.getText().toString());
                    edited.put("mobile",editPhone.getText().toString());
                    edited.put("nic",editNIC.getText().toString());

                    docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdminUpdateSecurity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MyProfile.class));
                            finish();
                        }
                    });
                }




            }
        });

    }
}
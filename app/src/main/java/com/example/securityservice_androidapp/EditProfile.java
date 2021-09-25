package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

            }
        });


    }
}
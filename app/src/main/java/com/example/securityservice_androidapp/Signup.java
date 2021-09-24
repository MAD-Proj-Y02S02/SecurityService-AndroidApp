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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    EditText emailET, passwordET,passwordchkET, fnameET, LNameET, mobileET,nicET;

    private Button RegisterBtn;

    private String email, password,passwordchk, fname,LName, mobile, nic;

    ValidateEmailPass validateEmailPass ;

    FirebaseAuth mAuth;

    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        validateEmailPass = new ValidateEmailPass(this);

        emailET = findViewById(R.id.emailreg_et);
        passwordET = findViewById(R.id.password_et);
        passwordchkET = findViewById(R.id.passwordchk_et);

        fnameET = findViewById(R.id.fName_et);
        LNameET = findViewById(R.id.LName_et);
        mobileET = findViewById(R.id.mobile_et);
        nicET = findViewById(R.id.nic_et);

        RegisterBtn = findViewById(R.id.register_btn);


        validateEmailPass = new ValidateEmailPass(this);

        RegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                handleRegisterBtnClick();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


    }


    private void handleRegisterBtnClick() {


        email = emailET.getText().toString().trim();
        password = passwordET.getText().toString().trim();
        passwordchk = passwordchkET.getText().toString().trim();
        fname = fnameET.getText().toString();
        LName = LNameET.getText().toString();
        mobile = mobileET.getText().toString();
        nic = nicET.getText().toString();



        if(fname.isEmpty()){
            Toast.makeText(this,"Enter First name", Toast.LENGTH_SHORT).show();
        }
        else if(LName.isEmpty()){
            Toast.makeText(this,"Enter Last name", Toast.LENGTH_SHORT).show();
        }
        else if(mobile.isEmpty()){
            Toast.makeText(this,"Enter Mobile ", Toast.LENGTH_SHORT).show();
        }
        else if(nic.isEmpty()){
            Toast.makeText(this,"Enter NIC ", Toast.LENGTH_SHORT).show();
        }
//      validating mail
        else if(validateEmailPass.checkEmailValid(email)&& validateEmailPass.checkPasswordValid(password)){
            //check two passwords match
            if(password.equals(passwordchk)){
                //creating new security with email and pass in authentication
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //creating  security obj
                            Security security = new Security(fname,LName,mobile,nic,email);
                            FirebaseUser fUser = mAuth.getCurrentUser();

                            addToFirestore(fUser ,security);

//                          addToDatabase(fUser ,security);

                            //adding security to realtime DB
//                            FirebaseDatabase.getInstance().getReference().child("Security").child(user.getUid()).child("name")
//                                    .setValue("cat").addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()){
//                                        Toast.makeText(Signup.this,"Successfully Registered", Toast.LENGTH_SHORT).show();
//                                    }
//                                    else{
//                                        Toast.makeText(Signup.this,"Registration Not Successful", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
                        }else{
                            Toast.makeText(Signup.this,"Registration Not Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else{
                Toast.makeText(this,"psaswords not matching", Toast.LENGTH_SHORT).show();
            }
        }




    }


//add security details to firestore
    private void addToFirestore(FirebaseUser fUser, Security security) {
        userID = fUser.getUid();
        DocumentReference documentReference = fStore.collection("Security").document(userID);
        Map<String,Object> user = new HashMap<>();
        user.put("fName",security.fname);
        user.put("lName",security.lname);
        user.put("mobile",security.mobile);
        user.put("nic",security.NIC);
        user.put("email",security.email);
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Signup.this,"FStore adding success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this,"FStore error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //tried adding to firebase db
//    private void addToDatabase(FirebaseUser user, Security security) {
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference rootReference = firebaseDatabase.getReference();
//
//        DatabaseReference nameReference = rootReference.child("Users").child(user.getUid()).child("name");
//        nameReference.setValue(fname);
//    }

}
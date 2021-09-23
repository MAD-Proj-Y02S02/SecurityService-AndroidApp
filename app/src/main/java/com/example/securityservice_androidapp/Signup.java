package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText email_et, password_et,passwordchk_et, fname_et, LName_et, mobile_et,nic_et;
    private Button signupBtn;

    private String email, password,passwordchk, fname,LName, mobile, nic;

    ValidateEmailPass validateEmailPass ;

//    FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        validateEmailPass = new ValidateEmailPass(this);

        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password);
        passwordchk_et = findViewById(R.id.passwordchk_et);

        fname_et = findViewById(R.id.fName_et);
        LName_et = findViewById(R.id.LName_et);
        mobile_et = findViewById(R.id.mobile_et);
        nic_et = findViewById(R.id.nic_et);

        signupBtn = findViewById(R.id.register_btn);

        validateEmailPass = new ValidateEmailPass(this);


        signupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                handleSignUpButtonClick();
            }
        });


    }

    private void handleSignUpButtonClick() {
        email = email_et.getText().toString();
        password = password_et.getText().toString();
        passwordchk = passwordchk_et.getText().toString();
        fname = fname_et.getText().toString();
        LName = LName_et.getText().toString();
        mobile = mobile_et.getText().toString();
        nic = nic_et.getText().toString();

        //validating mail
        if(validateEmailPass.checkEmailValid(email)&& validateEmailPass.checkEmailValid(password)){
            //check two passwords match
            if(password.equals(passwordchk)){



            }else{
                Toast.makeText(this,"psaswords not matching", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
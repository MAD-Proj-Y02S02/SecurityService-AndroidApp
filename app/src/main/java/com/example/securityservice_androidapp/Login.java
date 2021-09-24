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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText email_et, password_et;
    private Button signinBtn;
    private Button signupBtn;

    private String email, password;

    ValidateEmailPass validateEmailPass ;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //login
        validateEmailPass = new ValidateEmailPass(this);

        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password);

        signinBtn = findViewById(R.id.signin_btn);
        signupBtn = findViewById(R.id.signup_btn);

        signinBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id)
        {
            case R.id.signin_btn:
                handleSigninBtnClick();
                break;
            case R.id.signup_btn:
                handleSignupBtnClick();
                break;

            case R.id.adminsignin_btn:
                handleAdminMenuBtnClick();
                break;
        }

    }

    private void handleSigninBtnClick() {
         email = email_et.getText().toString();
         password = password_et.getText().toString();
        //TODO : login with email and pass
        if(validateEmailPass.checkEmailValid(email)&& validateEmailPass.checkPasswordValid(password)){
//            Toast.makeText(this,"Valid Inputs", Toast.LENGTH_SHORT).show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this,"Login Success "+email, Toast.LENGTH_SHORT).show();
                        //redirect to user acc
                        Intent intent = new Intent(Login.this, UserAccount.class);
                        startActivity(intent);                    }
                    else{
                        Toast.makeText(Login.this,"Login error "+task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }


    }

    public void handleSignupBtnClick(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public void handleAdminMenuBtnClick(){
        Intent intent = new Intent(this,adminHomePage.class);
        startActivity(intent);
    }
}
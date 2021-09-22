package com.example.securityservice_androidapp;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

public class ValidateEmailPass {
    Context context;

    ValidateEmailPass(Context context){
        this.context = context;
    }

    boolean checkEmailValid(String email){
        if(email.length()==0){
            Toast.makeText(context, "Please enter your email",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(context,"Please enter a valid email",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            //valid email
            return true;
        }
    }

    boolean checkPasswordValid(String password){
        if(password.length()==0){
            Toast.makeText(context, "Please enter the password",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.length()<6){
            Toast.makeText(context, "Please enter a password of at least 6 characters",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            //valid password
            return true;

        }
    }

}

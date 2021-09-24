package com.example.securityservice_androidapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOinvoice {

    private DatabaseReference databaseReference;
    public DAOinvoice(){

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://securityserviceapp-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = db.getReference(Invoice.class.getSimpleName());

    }

    public Task<Void> add(Invoice inv) {


        return databaseReference.push().setValue(inv);



    }



}

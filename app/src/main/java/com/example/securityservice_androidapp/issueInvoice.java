package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class issueInvoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_invoice);

        final EditText inputInvoiceID = findViewById(R.id.inputInvoiceID);
        final EditText inputSiteName = findViewById(R.id.inputSiteName);
        final EditText inputSiteContact = findViewById(R.id.inputSiteContact);
        final EditText inputDescription = findViewById(R.id.inputDescription);
        final EditText inputAmount = findViewById(R.id.inputAmount);

        Button issueBtn = findViewById(R.id.btnIssueInv);
        DAOinvoice dao = new DAOinvoice();
        issueBtn.setOnClickListener(view ->
        {
            System.out.println("setOnClickListener()");
            Invoice inv = new Invoice(
                    inputInvoiceID.getText().toString(),
                    inputSiteName.getText().toString(),
                    inputSiteContact.getText().toString(),
                    inputDescription.getText().toString(),
                    inputAmount.getText().toString()

                    );

            dao.add(inv).addOnSuccessListener(suc->{

                Toast.makeText(this,"Invoice Added",Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(err->{
                Toast.makeText(this,"Error: "+err.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });
    }
}
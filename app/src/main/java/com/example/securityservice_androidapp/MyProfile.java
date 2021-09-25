package com.example.securityservice_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyProfile extends AppCompatActivity {

    private TextView myprofileText , profileName, profileEmail, profilePhone,profileNIC;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    private Button resetpwd , changeProfile, redirectHome, deleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        myprofileText = findViewById(R.id.myprofile_txt);
        profileName = findViewById(R.id.profileName);
        profileEmail= findViewById(R.id.profileEmail);
        profilePhone= findViewById(R.id.profilePhone);
        profileNIC= findViewById(R.id.profileNIC);


        resetpwd = findViewById(R.id.pwdReset_btn);
        changeProfile = findViewById(R.id.changeProfile);
        redirectHome = findViewById(R.id.redirecthome_btn);
        deleteAccount = findViewById(R.id.deleteacc_btn);



        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("Security").document(user.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                myprofileText.setText("Hello " + documentSnapshot.getString("fName")+" ! \uD83D\uDC4B");
                profileName.setText( documentSnapshot.getString("fName")+" "+ documentSnapshot.getString("lName"));
                profileEmail.setText(documentSnapshot.getString("email"));
                profilePhone.setText(documentSnapshot.getString("mobile"));
                profileNIC.setText(documentSnapshot.getString("nic"));

            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfile.this, EditProfile.class);
                startActivity(intent);
            }
        });


//password reset
        resetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText resetPassword = new EditText(view.getContext());

                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter New Password with 6 or more characters.");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String newPassword = resetPassword.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MyProfile.this, "Password Reset Successfully.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MyProfile.this, "Password Reset Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close
                    }
                });

                passwordResetDialog.create().show();
            }
        });


        //delete an account
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final AlertDialog.Builder accountDeleteDialog = new AlertDialog.Builder(view.getContext());
                accountDeleteDialog.setTitle("Are you sure you want to delete account?");
                accountDeleteDialog.setMessage("This cannot be reverted");


                accountDeleteDialog.setPositiveButton("Yes, delete account", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        fStore.collection("Security").document(user.getUid()).delete();
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MyProfile.this, "User Acc Deleted", Toast.LENGTH_SHORT).show();
                                            Intent login = new Intent(MyProfile.this, Login.class);
                                            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(login);

                                        }else{
                                            Toast.makeText(MyProfile.this, "User Acc not Deleted", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                    }
                });

                accountDeleteDialog.setNegativeButton("Do not delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close
                    }
                });
                accountDeleteDialog.create().show();



            }
        });

        redirectHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MyProfile.this, UserAccount.class);
                startActivity(home);
            }
        });

    }

}
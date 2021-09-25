package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class UserAccount extends AppCompatActivity implements View.OnClickListener {

    TextView welcometext, profileWording,profileWordingGuard,assignStatus_txt;
    Button logout, myprofile,viewCurrentSite_btn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

//        welcometext = findViewById(R.id.welcometext);
        profileWording = findViewById(R.id.profileWording);
        profileWordingGuard = findViewById(R.id.textView21);
        logout = findViewById(R.id.logout_btn);
        myprofile = findViewById(R.id.myprofile_btn);
        assignStatus_txt = findViewById(R.id.assignStatus_txt);
        viewCurrentSite_btn = findViewById(R.id.viewCurrentSite_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("Security").document(user.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

//                welcometext.setText("Hello " + documentSnapshot.getString("fName")+" ! \uD83D\uDC4B");
                profileWording.setText(documentSnapshot.getString("fName")+" "+documentSnapshot.getString("lName"));
                profileWordingGuard.setText("Security Guard");
                if(documentSnapshot.getString("currentSite").equals("empty")){
                    assignStatus_txt.setText("Currently Available");
                }else{
                    assignStatus_txt.setText("Assigned to "+documentSnapshot.getString("currentSite"));
                }


            }
        });

        logout.setOnClickListener(this);
        viewCurrentSite_btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id)
        {
            case R.id.logout_btn:
                firebaseAuth.signOut();
                Intent home = new Intent(this, Login.class);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(home);
                this.finish();

                break;
            case R.id.myprofile_btn:
                Intent intent = new Intent(this, MyProfile.class);
                startActivity(intent);
                break;
            case R.id.viewCurrentSite_btn:
                Intent intent2 = new Intent(this, ViewCurrentJob.class);
                startActivity(intent2);
                break;



        }
    }
}




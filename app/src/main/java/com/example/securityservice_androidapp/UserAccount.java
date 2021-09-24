package com.example.securityservice_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
=======
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
>>>>>>> parent of 031fc11 (Merge pull request #8 from MAD-Proj-Y02S02/chamath)

public class UserAccount extends AppCompatActivity implements View.OnClickListener {

    TextView welcometext;
    Button logout;
    FirebaseAuth firebaseAuth;
<<<<<<< HEAD
    FirebaseFirestore fStore;

=======
>>>>>>> parent of 031fc11 (Merge pull request #8 from MAD-Proj-Y02S02/chamath)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        welcometext = findViewById(R.id.welcometext);
        logout = findViewById(R.id.logout_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

<<<<<<< HEAD
        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("Security").document(user.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                welcometext.setText("hello " + documentSnapshot.getString("fName")+" !");
            }
        });

        Toast.makeText(this,"Login error "+task.getException(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Login error "+task.getException(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Login error "+task.getException(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Login error "+task.getException(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Login error "+task.getException(), Toast.LENGTH_SHORT).show();




        logout.setOnClickListener(this);



=======
        welcometext.setText("Hi "+user.getEmail()+" !");

        logout.setOnClickListener(this);

>>>>>>> parent of 031fc11 (Merge pull request #8 from MAD-Proj-Y02S02/chamath)
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id)
        {
            case R.id.logout_btn:
                firebaseAuth.signOut();
                this.finish();
                break;

        }
    }
}




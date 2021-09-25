package com.example.securityservice_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssignSiteSelect extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AssignSignSelectAdapater assignSignSelectAdapater;
    ArrayList<Sites> list;
    TextView assignSiteUsername;
    private Security security;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_site_select);

        Security security = (Security) getIntent().getSerializableExtra("security");
        assignSiteUsername = findViewById(R.id.assignSiteUsername);
        assignSiteUsername.setText("Security guard "+security.getfName());



        recyclerView = findViewById(R.id.view_list);
        databaseReference = FirebaseDatabase.getInstance("https://securityserviceapp-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Sites");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        assignSignSelectAdapater= new AssignSignSelectAdapater(this,list);
        recyclerView.setAdapter(assignSignSelectAdapater);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Sites site = dataSnapshot.getValue(Sites.class);
                    list.add(site);
                    site.setSecurityID(security.getID());

                }
                assignSignSelectAdapater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.securityservice_androidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AdminViewAllGuards extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Security> securityArrayList;
    SecurityAdapter securityAdapter;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_guards);

        recyclerView = findViewById(R.id.viewAllinvoices);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fStore = FirebaseFirestore.getInstance();
        securityArrayList = new ArrayList<Security>();
        securityAdapter = new SecurityAdapter(AdminViewAllGuards.this,securityArrayList);

        recyclerView.setAdapter(securityAdapter);

        EventChangeListner();
    }

    private void EventChangeListner() {
        fStore.collection("Security")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            Log.e("firestore error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc: value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                securityArrayList.add(dc.getDocument().toObject(Security.class));
                            }
                            securityAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }
}
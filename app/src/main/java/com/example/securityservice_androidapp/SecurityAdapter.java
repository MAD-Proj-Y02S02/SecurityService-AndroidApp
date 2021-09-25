package com.example.securityservice_androidapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecurityAdapter extends RecyclerView.Adapter<SecurityAdapter.SecurityViewHolder>{
    Context context;

    public SecurityAdapter(Context context, ArrayList<Security> securityArrayList) {
        this.context = context;
        this.securityArrayList = securityArrayList;
    }

    ArrayList<Security> securityArrayList;


    @NonNull
    @Override
    public SecurityAdapter.SecurityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.securitycard,parent,false);

        return new SecurityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SecurityAdapter.SecurityViewHolder holder, int position) {
        Security security = securityArrayList.get(position);

        holder.fname.setText(security.fName);
        holder.lname.setText(security.lName);
        holder.mobile.setText(security.mobile);
        holder.email.setText(security.email);
        holder.nic.setText(security.nic);

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AdminUpdateSecurity.class);
                intent.putExtra("security",security);
                context.startActivity(intent);

            }
        });

        holder.bTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, AssignSiteSelect.class);
                intent2.putExtra("security",security);
                context.startActivity(intent2);
            }
        });

    }

    @Override
    public int getItemCount() {

        return securityArrayList.size();
    }


    public static class SecurityViewHolder extends RecyclerView.ViewHolder{

        TextView fname, lname, nic,email,mobile;
        ImageView updateBtn;
        Button bTn;

        public SecurityViewHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.firstName);
            lname = itemView.findViewById(R.id.lastName);
            mobile = itemView.findViewById(R.id.mobile);
            email = itemView.findViewById(R.id.email);
            nic = itemView.findViewById(R.id.nic);
            bTn = itemView.findViewById(R.id.delete_btn);
            updateBtn = itemView.findViewById(R.id.update_btn);
        }
    }

}

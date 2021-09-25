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

public class AssignSignSelectAdapater extends RecyclerView.Adapter<AssignSignSelectAdapater.MyViewHolder>{



    Context context;

    ArrayList<Sites> list;

    public AssignSignSelectAdapater(Context context, ArrayList<Sites> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AssignSignSelectAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.siteviewcard,parent, false);
        return new AssignSignSelectAdapater.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignSignSelectAdapater.MyViewHolder holder, int position) {

        Sites site = list.get(position);
        holder.site.setText(site.getSiteName());
        holder.owner.setText(site.getSiteOwner());
        holder.number.setText(site.getSiteNumber());
        holder.address.setText((site.getSiteAddress()));

        holder.selectIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, AssignSitetoSecurity.class);
                intent2.putExtra("site",site);
                context.startActivity(intent2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView site, owner, address, number;
        Button selectIco;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            site = itemView.findViewById(R.id.site_txt);
            owner = itemView.findViewById(R.id.siteowner_txt);
            address = itemView.findViewById(R.id.address_txt);
            number = itemView.findViewById(R.id.sitecontact_txt);
            selectIco = itemView.findViewById(R.id.selectico);
        }
    }
}

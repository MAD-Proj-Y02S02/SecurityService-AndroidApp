package com.example.securityservice_androidapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;

    ArrayList<Sites> list;

    public MyAdapter(Context context, ArrayList<Sites> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Sites site = list.get(position);
        holder.site.setText(site.getSiteName());
        holder.owner.setText(site.getSiteOwner());
        holder.number.setText(site.getSiteNumber());
        holder.address.setText((site.getSiteAddress()));

        holder.updateSite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intenEditSite = new Intent(context, EditSiteAdmin.class);
                intenEditSite.putExtra("site",site);
                context.startActivity(intenEditSite);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView site, owner, address, number;
        ImageView updateSite_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            site = itemView.findViewById(R.id.site_txt);
            owner = itemView.findViewById(R.id.siteowner_txt);
            address = itemView.findViewById(R.id.address_txt);
            number = itemView.findViewById(R.id.sitecontact_txt);
            updateSite_btn = itemView.findViewById(R.id.updateSite_btn);
        }
    }
}

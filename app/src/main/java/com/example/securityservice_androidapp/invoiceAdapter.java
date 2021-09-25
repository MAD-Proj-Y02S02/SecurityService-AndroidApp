package com.example.securityservice_androidapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class invoiceAdapter extends RecyclerView.Adapter<invoiceAdapter.invoiceViewHolder> {

    Context context;
    ArrayList<InvoiceData> list;



    public invoiceAdapter(Context context, ArrayList<InvoiceData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public invoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.invoicelist,parent,false);
        return new invoiceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull invoiceViewHolder holder, int position) {

        InvoiceData invoiceData = list.get(position);
        holder.RinvoiceID.setText(invoiceData.getInvoiceID());
        holder.RsiteName.setText(invoiceData.getSiteNameInv());
        holder.RsiteContact.setText(invoiceData.getSiteContact());
        holder.RsiteDesc.setText(invoiceData.getSiteDescription());
        holder.Ramount.setText(invoiceData.getSiteAmount());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class invoiceViewHolder extends RecyclerView.ViewHolder{

        TextView RinvoiceID,RsiteName,RsiteContact,RsiteDesc,Ramount;
        ImageView delete,update;

        public invoiceViewHolder(@NonNull View itemView) {
            super(itemView);

            RinvoiceID = itemView.findViewById(R.id.recycleInvoiceID);
            RsiteName = itemView.findViewById(R.id.recycleSiteName);
            RsiteContact = itemView.findViewById(R.id.recycleSiteContact);
            RsiteDesc = itemView.findViewById(R.id.recycleSiteDesc);
            Ramount = itemView.findViewById(R.id.recycleAmount);




        }
    }


}

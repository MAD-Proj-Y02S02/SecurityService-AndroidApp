package com.example.securityservice_androidapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InvoiceVH extends RecyclerView.ViewHolder {

    public TextView InvoiceID,SiteName,SiteContact,SiteDescription,SiteAmount;

    public InvoiceVH(@NonNull View itemView) {
        super(itemView);

        InvoiceID = itemView.findViewById(R.id.recycleInvoiceID);
        SiteName = itemView.findViewById(R.id.recycleSiteName);
        SiteContact = itemView.findViewById(R.id.recycleSiteContact);
        SiteDescription = itemView.findViewById(R.id.recycleSiteDesc);
        SiteAmount = itemView.findViewById(R.id.recycleAmount);
    }
}

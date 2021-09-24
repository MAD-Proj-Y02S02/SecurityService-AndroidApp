package com.example.securityservice_androidapp;

public class Invoice {

    private String invoiceID;
    private String siteNameInv;
    private String siteContact;
    private String siteDescription;
    private String siteAmount;

    public Invoice(){}
    public Invoice(String invoiceID, String siteName, String siteContact, String siteDescription, String siteAmount) {
        this.invoiceID = invoiceID;
        this.siteNameInv = siteName;
        this.siteContact = siteContact;
        this.siteDescription = siteDescription;
        this.siteAmount = siteAmount;
    }

    public String getInvoiceID(){
        return invoiceID;
    }

    public void setInvoiceID(){
        this.invoiceID = invoiceID;
    }




    public String getSiteNameInv(){
        return siteNameInv;
    }

    public void setSiteNameInv(){
        this.siteNameInv = siteNameInv;
    }



    public String getSiteContact(){
        return siteContact;
    }

    public void setSiteContact(){
        this.siteContact = siteContact;
    }



    public String getSiteDescription(){
        return siteDescription;
    }

    public void setSiteDescription(){
        this.siteDescription = siteDescription;
    }




    public String getSiteAmount(){
        return siteAmount;
    }

    public void setSiteAmount(){
        this.siteAmount = siteAmount;
    }


}

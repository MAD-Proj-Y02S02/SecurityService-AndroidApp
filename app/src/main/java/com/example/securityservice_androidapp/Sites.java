package com.example.securityservice_androidapp;


import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Sites implements Serializable {

    private String siteName;
    private String siteOwner;
    private String siteNumber;
    private String siteAddress;

    @Exclude
    private String SecurityID;

    public String getSecurityID() {
        return SecurityID;
    }

    public void setSecurityID(String securityID) {
        SecurityID = securityID;
    }

    public Sites() {
    }

    public Sites(String siteName, String siteOwner, String siteNumber, String siteAddress) {
        this.siteName = siteName;
        this.siteOwner = siteOwner;
        this.siteNumber = siteNumber;
        this.siteAddress = siteAddress;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteOwner() {
        return siteOwner;
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public String getSiteAddress() {
        return siteAddress;
    }



//    public String getSiteAddress(String trim) {
//        return siteAddress;
//    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setSiteOwner(String siteOwner) {
        this.siteOwner = siteOwner;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public void setSiteAddress (String siteAddress) {
        this.siteAddress = siteAddress;
    }
}

package com.example.securityservice_androidapp;


public class Sites {

    private String siteName;
    private String siteOwner;
    private String siteNumber;
    private String siteAddress;

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

    public String getSiteAddress(String trim) {
        return siteAddress;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setSiteOwner(String siteOwner) {
        this.siteOwner = siteOwner;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }
}

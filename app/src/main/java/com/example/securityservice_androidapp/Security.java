package com.example.securityservice_androidapp;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Security implements Serializable {
    public String fName, lName, mobile, nic, email, currentSite;
    @Exclude private String ID;

    public Security(){

    }
    public Security(String fName,String lName,String mobile,String nic ,String email){
        this.fName = fName;
        this.lName = lName;
        this.mobile = mobile;
        this.nic = nic;
        this.email = email;

    }

    public String getCurrentSite() {
        return currentSite;
    }

    public void setCurrentSite(String currentSite) {
        this.currentSite = currentSite;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNIC() {
        return nic;
    }

    public void setNIC(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.example.securityservice_androidapp;

public class Security {
    public String fName, lName, mobile, nic, email;

    public Security(){

    }
    public Security(String fName,String lName,String mobile,String nic ,String email){
        this.fName = fName;
        this.lName = lName;
        this.mobile = mobile;
        this.nic = nic;
        this.email = email;

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

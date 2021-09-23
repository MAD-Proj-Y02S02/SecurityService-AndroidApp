package com.example.securityservice_androidapp;

public class Security {
    public String fname, lname, mobile, NIC, email;

    public Security(){

    }
    public Security(String fname,String lname,String mobile,String NIC ,String email){
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.NIC = NIC;
        this.email = email;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

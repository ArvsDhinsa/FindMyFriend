package com.example.myapplication;

public class User {

    public String userName;
    public String userPassword;
    public String mobileNo;
    public String email;

    public User() {

    }

    public User(String userName, String password, String mobile, String email) {
        this.userName = userName;
        this.userPassword = password;
        this.mobileNo = mobile;
        this.email = email;
    }
}

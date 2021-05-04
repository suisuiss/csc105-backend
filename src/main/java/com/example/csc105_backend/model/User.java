package com.example.csc105_backend.model;

import java.sql.ResultSet;

public class User {
    int userid;
    String email;
    String firstname;
    String lastname;
    String username;
    String password;
    String address;
    String phoneNumber;

    public User(int userid, String email, String firstname, String lastname, String username, String password, String address, String phoneNumber) {
        this.userid = userid;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public User (ResultSet resultSet) throws Exception{
        userid=resultSet.getInt("userid");
        email = resultSet.getString("email");
        firstname=resultSet.getString("firstname");
        lastname=resultSet.getString("lastname");
        username=resultSet.getString("username");
        password=resultSet.getString("password");
        address=resultSet.getString("address");
        phoneNumber=resultSet.getString("phoneNumber");

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

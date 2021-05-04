package com.example.csc105_backend.model;

import java.sql.ResultSet;

public class Signup {
    String name;
    String surname;
    String addresss;
    String phoneNumber;
    String email;
    String username;
    String password;

    public Signup(String name, String surname, String addresss, String phoneNumber, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.addresss = addresss;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public Signup(ResultSet resultSet)throws Exception {
        name=resultSet.getString("firstname");
        surname=resultSet.getString("lastname");
        addresss=resultSet.getString("address");
        phoneNumber=resultSet.getString("phoneNumber");
        email=resultSet.getString("email");
        username=resultSet.getString("username");
        password=resultSet.getString("password");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNum) {
        this.phoneNumber = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

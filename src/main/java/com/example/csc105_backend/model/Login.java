package com.example.csc105_backend.model;

import java.sql.ResultSet;

public class Login {
String username;
String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Login(ResultSet resultSet)throws Exception{
        username = resultSet.getString("username");
        password = resultSet.getString("password");
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

package com.example.csc105_backend.Model;

import java.sql.ResultSet;

public class Login {
String username;
String password;
int userid;

    public Login(String username, String password,int userid) {
        this.username = username;
        this.password = password;
        this.userid = userid;
    }
    public Login(ResultSet resultSet)throws Exception{
        username = resultSet.getString("username");
        password = resultSet.getString("password");
        userid=resultSet.getInt("userid");
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}

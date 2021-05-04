package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginOperation {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Login login;
    public int checkLogin (String username, String password){
        try{
            connection = DBconnection.getMySQLConnection();
            preparedStatement=connection.prepareStatement("SELECT * "+"FROM User WHERE username LIKE ?");
            preparedStatement.setString(1,username);
            resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                login = new Login(resultSet);
            }return login.getUserid();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;


    }
}

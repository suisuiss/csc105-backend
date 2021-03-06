package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginOperation {
    public Login checkLogin (String username, String password){
        Login login = null;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ? ")){
            int index = 1;
            preparedStatement.setString(index++,username);
            preparedStatement.setString(index++,password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    login = new Login(resultSet);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return login;
    }
}

package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupOperation {
    public boolean register(String firstname,String lastname,String address,String phoneNumber,String email,String username,String password)throws Exception {
        boolean result = false;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User(email, firstname, lastname, username, password, address, phoneNumber) VALUES(?, ?, ?, ?, ?, ?, ?)")){
            int index = 1;
            preparedStatement.setString(index++,email);
            preparedStatement.setString(index++,firstname);
            preparedStatement.setString(index++,lastname);
            preparedStatement.setString(index++,username);
            preparedStatement.setString(index++,password);
            preparedStatement.setString(index++,address);
            preparedStatement.setString(index++,phoneNumber);
            int rs = preparedStatement.executeUpdate();
            if(rs > 0) {
                result = true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

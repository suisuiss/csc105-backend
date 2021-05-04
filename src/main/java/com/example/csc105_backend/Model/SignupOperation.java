package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupOperation {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Signup signup;
public String register(String name,String surname,String address,String phoneNumber,String email,String username,String password,int userid) {
    try {
        connection = DBconnection.getMySQLConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE firstname,lastname,address,phoneNumber,email,username,password");
        preparedStatement.setInt(1,userid);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Signup signup = new Signup(resultSet);
            preparedStatement = connection.prepareStatement("INSERT INTO User (firstname,lastname,address,phoneNumber,email,username,password) VALUES(?)");
            preparedStatement.setString(1,resultSet.getString("firstname"));
            preparedStatement.setString(1,resultSet.getString("lastname"));
            preparedStatement.setString(1,resultSet.getString("address"));
            preparedStatement.setString(1,resultSet.getString("phoneNumber"));
            preparedStatement.setString(1,resultSet.getString("email"));
            preparedStatement.setString(1,resultSet.getString("username"));
            preparedStatement.setString(1,resultSet.getString("password"));
            preparedStatement.executeUpdate();
            return "Successful Register";
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    return "FAILED REGISTER";
}

}

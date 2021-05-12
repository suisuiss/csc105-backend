package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindUserById {
    public User findUserById (int userId){
        User user = null;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE user_id = ? ")){
            int index = 1;
            preparedStatement.setInt(index++,userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    user = new User(resultSet);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}

package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateUser {
    public boolean updateUser(User user) throws Exception{
        boolean result = false;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE User SET email = ?, firstname = ?, lastname = ?, address = ?, phoneNumber = ? WHERE user_id = ? ")){
            int index = 1;
            preparedStatement.setString(index++,user.getEmail());
            preparedStatement.setString(index++,user.getFirstname());
            preparedStatement.setString(index++,user.getLastname());
            preparedStatement.setString(index++,user.getAddress());
            preparedStatement.setString(index++,user.getPhoneNumber());
            preparedStatement.setInt(index++, user.getUserid());
            int rs = preparedStatement.executeUpdate();
            if(rs > 0) {
                result = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

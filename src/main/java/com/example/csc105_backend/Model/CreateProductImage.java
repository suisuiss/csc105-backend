package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateProductImage {
    public int createProductImage(ProductImage prdImage) {
        int result = 0;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Product_Image(product_id, product_pic_url) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)){
            int index = 1;
            preparedStatement.setInt(index++,prdImage.getProductId());
            preparedStatement.setString(index++,prdImage.getProductPicUrl());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateProduct {
    public int createProduct(Product product) throws Exception{
        int result = 0;

        try(Connection connection =DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Product(seller_id, product_name, product_price, product_amount, product_details, product_desc, product_category) VALUES(?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)){
            int index = 1;
            preparedStatement.setInt(index++,product.getSellerId());
            preparedStatement.setString(index++,product.getProductName());
            preparedStatement.setBigDecimal(index++,product.getProductPrice());
            preparedStatement.setInt(index++,product.getProductAmount());
            preparedStatement.setString(index++,product.getProductDetails());
            preparedStatement.setString(index++,product.getProductDesc());
            preparedStatement.setString(index++,product.getProductCategory());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

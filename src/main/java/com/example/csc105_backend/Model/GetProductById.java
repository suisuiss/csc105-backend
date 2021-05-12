package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetProductById {
    public Product getProductById(int productId) throws Exception{
        Product result = null;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Product WHERE product_id = ? ")){
            preparedStatement.setInt(1,productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    result = new Product();
                    result.setProductId(resultSet.getInt("product_id"));
                    result.setBuyerId(resultSet.getInt("buyer_id"));
                    result.setSellerId(resultSet.getInt("seller_id"));
                    result.setProductName(resultSet.getString("product_name"));
                    result.setProductPrice(resultSet.getBigDecimal("product_price"));
                    result.setProductAmount(resultSet.getInt("product_amount"));
                    result.setProductDetails(resultSet.getString("product_details"));
                    result.setProductDesc(resultSet.getString("product_desc"));
                    result.setProductCategory(resultSet.getString("product_category"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

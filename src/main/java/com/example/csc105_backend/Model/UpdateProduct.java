package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateProduct {
    public boolean updateProduct(Product product) throws Exception{
        boolean result = false;
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Product SET ");
        sql.append("product_name = ?, ");
        sql.append("product_price = ?, ");
        sql.append("product_amount = ?, ");
        sql.append("product_details = ?, ");
        sql.append("product_desc = ?, ");
        sql.append("product_category = ? ");
        sql.append(" WHERE product_id = ? ");

        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
            int index = 1;
            preparedStatement.setString(index++,product.getProductName());
            preparedStatement.setBigDecimal(index++,product.getProductPrice());
            preparedStatement.setInt(index++,product.getProductAmount());
            preparedStatement.setString(index++,product.getProductDetails());
            preparedStatement.setString(index++,product.getProductDescription());
            preparedStatement.setString(index++,product.getProductCategory());
            preparedStatement.setInt(index++,product.getProductId());
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

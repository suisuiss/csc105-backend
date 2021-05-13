package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductList {

    public List<Product> getProductList() throws Exception {
        List<Product> result = new ArrayList();
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Product ")){
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Product product = null;
                while(resultSet.next()){
                    product = new Product();
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setBuyerId(resultSet.getInt("buyer_id"));
                    product.setSellerId(resultSet.getInt("seller_id"));
                    product.setProductName(resultSet.getString("product_name"));
                    product.setProductPrice(resultSet.getBigDecimal("product_price"));
                    product.setProductAmount(resultSet.getInt("product_amount"));
                    product.setProductDetails(resultSet.getString("product_details"));
                    product.setProductDescription(resultSet.getString("product_desc"));
                    product.setProductCategory(resultSet.getString("product_category"));

                    result.add(product);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

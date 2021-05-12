package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteProductImage {
    public boolean deleteProductImage(int productId) {
        boolean result = false;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Product_Image WHERE product_id = ?")){
            int index = 1;
            preparedStatement.setInt(index++,productId);
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteProduct {
    public boolean deleteProduct(int productId) {
        boolean result = false;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Product WHERE product_id = ?")){
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

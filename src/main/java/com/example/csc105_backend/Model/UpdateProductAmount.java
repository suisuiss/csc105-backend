package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateProductAmount {
    public boolean updateProductAmt(int productId, int productAmt) throws Exception{
        boolean result = false;
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Product SET product_amount = ? WHERE product_id = ? ")){
            int index = 1;
            preparedStatement.setInt(index++,productAmt);
            preparedStatement.setInt(index++,productId);

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

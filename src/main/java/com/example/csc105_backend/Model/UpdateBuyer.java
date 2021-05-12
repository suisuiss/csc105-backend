package com.example.csc105_backend.Model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateBuyer {
    public boolean updateProductBuyer(int productId, int buyerId, BigDecimal productPrice) throws Exception{
        boolean result = false;
        String sql = "UPDATE Product SET buyer_id = ?, product_price = ? WHERE product_id = ? ";

        try(Connection conn=DBconnection.getMySQLConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;
            ps.setInt(index++,buyerId);
            ps.setBigDecimal(index++,productPrice);
            ps.setInt(index++,productId);
            int rs = ps.executeUpdate();
            if(rs > 0) {
                result = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

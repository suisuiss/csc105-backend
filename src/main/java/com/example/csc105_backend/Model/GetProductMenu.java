package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetProductMenu {
    public List<String> getProductMenu() throws Exception{
        List<String> result = new ArrayList<String>();
        String sql = "SELECT DISTINCT product_category FROM Product  ";
        try(Connection conn=DBconnection.getMySQLConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()) {
                    result.add("product");
                while(rs.next()){
                    result.add(rs.getString("product_category"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

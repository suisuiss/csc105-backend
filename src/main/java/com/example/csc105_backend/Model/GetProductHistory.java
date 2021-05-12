package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetProductHistory {
    public List<Product> getProductHistory(int userId,String userType) throws Exception{
        List<Product> result = new ArrayList<Product>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Product WHERE 1=1 ");
        if("BUYER".equals(userType)){
            sql.append(" AND buyer_id = ? ");
        }else{
            sql.append(" AND seller_id = ? ");
        }

        try(Connection conn=DBconnection.getMySQLConnection();PreparedStatement ps = conn.prepareStatement(sql.toString())){
            if("BUYER".equals(userType)){
                ps.setInt(1,userId);
            }else{
                ps.setInt(1,userId);
            }

            try (ResultSet rs = ps.executeQuery()) {
                Product bean = null;
                while(rs.next()){
                    bean = new Product();
                    bean.setProductId(rs.getInt("product_id"));
                    bean.setBuyerId(rs.getInt("buyer_id"));
                    bean.setSellerId(rs.getInt("seller_id"));
                    bean.setProductName(rs.getString("product_name"));
                    bean.setProductPrice(rs.getBigDecimal("product_price"));
                    bean.setProductAmount(rs.getInt("product_amount"));
                    bean.setProductDetails(rs.getString("product_details"));
                    bean.setProductDesc(rs.getString("product_desc"));
                    bean.setProductCategory(rs.getString("product_category"));

                    result.add(bean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

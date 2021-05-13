package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetProductByFilter {
    public List<Product> getProductByFilter(String prodCat) throws Exception{
        List<Product> result = new ArrayList<Product>();
        String sql = "SELECT * FROM Product WHERE product_category = ? ";
        try(Connection conn=DBconnection.getMySQLConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,prodCat);
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
                    bean.setProductDescription(rs.getString("product_desc"));
                    bean.setProductCategory(rs.getString("product_category"));
                    result.add(bean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Product> getProductBySearch(String prod) throws Exception{
        List<Product> result = new ArrayList<Product>();
        String sql = "SELECT * FROM Product WHERE product_name like ? ";
        try(Connection conn=DBconnection.getMySQLConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,"%"+prod+"%");
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
                    bean.setProductDescription(rs.getString("product_desc"));
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

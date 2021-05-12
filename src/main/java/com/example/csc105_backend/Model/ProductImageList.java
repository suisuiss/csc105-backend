package com.example.csc105_backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductImageList {	List<ProductImage> result = new ArrayList<ProductImage>();
    public List<ProductImage> getProductImgList(int productId) throws Exception{
        List<ProductImage> result = new ArrayList<ProductImage>();
        try(Connection connection=DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Product_Image WHERE product_id = ? ")){
            preparedStatement.setInt(1,productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ProductImage productImage = null;
                while(resultSet.next()){
                    productImage = new ProductImage();
                    productImage.setProductId(resultSet.getInt("product_id"));
                    productImage.setProductPicUrl(resultSet.getString("product_pic_url"));

                    result.add(productImage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}

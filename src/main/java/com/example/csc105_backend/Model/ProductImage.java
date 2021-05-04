package com.example.csc105_backend.Model;

import java.sql.ResultSet;

public class ProductImage {
    int id;
    int productId;
    String productPicUrl;

    public ProductImage(ResultSet resultSet)throws Exception{
        id=resultSet.getInt("id");
        productId=resultSet.getInt("product_id");
        productPicUrl=resultSet.getString("product_pic_url");
    }
    public ProductImage(int id, int productId, String productPicUrl) {
        this.id = id;
        this.productId = productId;
        this.productPicUrl = productPicUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
    }
}

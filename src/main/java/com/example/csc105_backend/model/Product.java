package com.example.csc105_backend.model;

import java.sql.ResultSet;

public class Product {
    int productId;
    int buyerId;
    int sellerId;
    String productName;
    double productPrice;
    int productAmount;
    String productDetails;
    String productDesc;
    String productCategory;

    public Product (ResultSet resultSet)throws Exception{
        productId=resultSet.getInt("product_id");
        buyerId=resultSet.getInt("buyer_id");
        sellerId=resultSet.getInt("seller_id");
        productName=resultSet.getString("product_name");
        productPrice=resultSet.getInt("product_price");
        productAmount=resultSet.getInt("product_amount");
        productDetails=resultSet.getString("product_details");
        productDesc=resultSet.getString("product_desc");
        productCategory=resultSet.getString("product_category");
    }
    public Product(int productId, int buyerId, int sellerId, String productName, double productPrice, int productAmount, String productDetails, String productDesc, String productCategory) {
        this.productId = productId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.productDetails = productDetails;
        this.productDesc = productDesc;
        this.productCategory = productCategory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}

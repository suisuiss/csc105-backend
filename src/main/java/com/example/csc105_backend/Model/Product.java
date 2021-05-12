package com.example.csc105_backend.Model;

import java.sql.ResultSet;
import java.math.BigDecimal;

public class Product {
    int productId;
    int buyerId;
    int sellerId;
    String productName;
    BigDecimal productPrice;
    int productAmount;
    String productDetails;
    String productDesc;
    String productCategory;
    String productPics;

    public Product(int productId, int buyerId, int sellerId, String productName, BigDecimal productPrice, int productAmount, String productDetails, String productDesc, String productCategory,String productPics) {
        this.productId = productId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.productDetails = productDetails;
        this.productDesc = productDesc;
        this.productCategory = productCategory;
        this.productPics=productPics;
    }

    public Product() {
        super();
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
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

    public String getProductPics() {
        return productPics;
    }

    public void setProductPics(String productPics) {
        this.productPics = productPics;
    }
}

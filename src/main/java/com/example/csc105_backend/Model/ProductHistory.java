package com.example.csc105_backend.Model;

import java.math.BigDecimal;

public class ProductHistory {
    private int buyerID;
    private int productID;
    private double productPrice;
    private int productAmount;

    public ProductHistory(int buyerID, int productID, double productPrice, int productAmount) {
        this.buyerID = buyerID;
        this.productID = productID;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    public ProductHistory() {
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
}

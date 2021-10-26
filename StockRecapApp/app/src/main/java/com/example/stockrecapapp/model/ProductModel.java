package com.example.stockrecapapp.model;

public class ProductModel {

    private String productid;
    private String productname;
    private int productstock;

    public ProductModel(String productid, String productname, int productstock) {
        this.productid = productid;
        this.productname = productname;
        this.productstock = productstock;
    }

    public String getproductid() {
        return productid;
    }

    public String getproductname() {
        return productname;
    }

    public int getproductstock() {
        return productstock;
    }
}

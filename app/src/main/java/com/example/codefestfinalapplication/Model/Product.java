package com.example.codefestfinalapplication.Model;

public class Product {
    String product_name;
    String product_image_path;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image_path() {
        return product_image_path;
    }

    public void setProduct_image_path(String product_image_path) {
        this.product_image_path = product_image_path;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    double product_price;

    public Product(String product_name, String product_image_path, String status, String description, double product_price) {
        this.product_name = product_name;
        this.product_image_path = product_image_path;
        this.status = status;
        this.description = description;
        this.product_price = product_price;
    }

    public Product() {

    }
}

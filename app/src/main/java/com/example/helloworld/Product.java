package com.example.helloworld;
// Model dữ liệu sản phẩm
public class Product {
    String name;
    int price;
    int productID;

    public Product(int productID, String name, int price) {
        this.name = name;
        this.price = price;
        this.productID = productID;
    }
}

package com.decroly.backcarrito.model;

public class Product
{
    private String SKU;
    private String title;
    private String price;

    Product(String SKU, String title, String price)
    {
        this.SKU = SKU;
        this.title = title;
        this.price = price;
    }

    public String getSku() {
        return SKU;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}

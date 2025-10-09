package com.decroly.backcarrito.model;

import java.util.ArrayList;
import java.util.List;

public class Shop
{
    private String currency;
    private List<Product> products;

    public Shop(String currency) {
        this.currency = currency;
        this.products = new ArrayList<>();

        // Inserto productos en la tienda
        this.products.add(new Product("0K3QOSOV4V","iFhone 13 Pro","938.99"));
        this.products.add(new Product("TGD5XORY1L","Cargador","49.99"));
        this.products.add(new Product("IOKW9BQ9F3","Funda de piel","79.99"));
        
    }

    public String getCurrency()
    {
        return currency;
    }

    public List<Product> getProducts()
    {
        return products;
    }
}

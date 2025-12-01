package com.decroly.backcarrito.model;

public class Product
{
    private Number id;
    private String title;
    private String subtitle;
    private String descripcion;
    private String url;
    private String image;
    private String price;

    private String categoria;  // ← NUEVO CAMPO

    public Product(
        Number id,
        String title,
        String subtitle,
        String descripcion,
        String url,
        String image,
        String categoria,
        String price // ← NUEVO PARÁMETRO
    ) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.descripcion = descripcion;
        this.url = url;
        this.image = image;
        this.categoria = categoria;
        this.price = price;
    }

    public Number getId() { return id; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getDescripcion() { return descripcion; }
    public String getUrl() { return url; }
    public String getImage() { return image; }
    public String getPrice() { return price; }

    public String getCategoria() {   // ← GETTER NUEVO
        return categoria;
    }
}

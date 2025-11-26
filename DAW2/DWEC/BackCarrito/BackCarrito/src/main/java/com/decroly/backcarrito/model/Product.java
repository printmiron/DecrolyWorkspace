package com.decroly.backcarrito.model;

public class Product
{
    private Number id;
    private String title;
    private String subtitle;
    private String descripcion;
    private String url;
    private String image;

    

    public Product(Number id, String title, String subtitle, String descripcion, String url, String image) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.descripcion = descripcion;
        this.url = url;
        this.image = image;
    }



    public Number getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }



    public String getSubtitle() {
        return subtitle;
    }



    public String getDescripcion() {
        return descripcion;
    }



    public String getUrl() {
        return url;
    }



    public String getImage() {
        return image;
    }

   
}

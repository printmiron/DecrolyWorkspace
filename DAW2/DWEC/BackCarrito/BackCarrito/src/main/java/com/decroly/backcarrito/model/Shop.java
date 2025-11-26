package com.decroly.backcarrito.model;

import java.util.ArrayList;
import java.util.List;

public class Shop {

  
    private List<Product> products;

    public Shop() {
        
        this.products = new ArrayList<>();

        // Inserto productos en la tienda
        this.products.add(new Product(1, "Cámara Fotográfica Pro", "Resolución 4K UHD", "Cámara profesional con estabilización óptica y grabación en 4K, ideal para fotografía y video.", "camara-pro", "https://picsum.photos/seed/camara/600/400"));

        this.products.add(new Product(
                2,
                "Cámara Deportiva ActionX",
                "Video 4K y fotos 20MP",
                "Cámara deportiva resistente al agua con gran angular y estabilización avanzada, ideal para actividades extremas.",
                "camara-actionx",
                "https://picsum.photos/seed/actionx/600/400"
        ));

        this.products.add(new Product(
                3,
                "Cámara Mirrorless AlphaLite",
                "Sensor APS-C 24MP",
                "Cámara mirrorless ligera con enfoque automático rápido y grabación en 4K, perfecta para creadores de contenido.",
                "camara-alphalite",
                "https://picsum.photos/seed/alphalite/600/400"
        ));

        this.products.add(new Product(
                4,
                "Cámara Compacta TravelShot",
                "Zoom óptico 30x",
                "Cámara compacta ideal para viajes, con zoom potente, WiFi integrado y captura nocturna mejorada.",
                "camara-travelshot",
                "https://picsum.photos/seed/travelshot/600/400"
        ));

        this.products.add(new Product(
                5,
                "Cámara Profesional StudioMax",
                "Sensor Full Frame 45MP",
                "Cámara profesional de alto rendimiento con rango dinámico ampliado y grabación en 8K para producción audiovisual.",
                "camara-studiomax",
                "https://picsum.photos/seed/studiomax/600/400"
        ));

    }

   

    public List<Product> getProducts() {
        return products;
    }
}

package com.decroly.backcarrito.model;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private List<Product> products;

    public Shop() {

        this.products = new ArrayList<>();

        this.products.add(new Product(
            1,
            "Auriculares Inalámbricos SoundWave Pro",
            "Cancelación Activa de Ruido (ANC)",
            "Auriculares premium con cancelación activa de ruido, modo transparencia y 30 horas de batería.",
            "auriculares-soundwave-pro",
            "https://images.pexels.com/photos/4523027/pexels-photo-4523027.jpeg",
            "Auriculares",
            "200.25"  // ← categoría
        ));

        this.products.add(new Product(
            2,
            "Smartwatch FitPulse X",
            "Monitorización 24/7",
            "Reloj inteligente con seguimiento de ritmo cardiaco y GPS integrado.",
            "smartwatch-fitpulse-x",
            "https://images.pexels.com/photos/1682821/pexels-photo-1682821.jpeg",
            "Smartwatch",
            "250"
        ));

        this.products.add(new Product(
            3,
            "Tablet UltraTab 10.5",
            "Pantalla Full HD+ 10.5\"",
            "Tablet ligera con procesador octa-core y gran autonomía.",
            "tablet-ultratab-105",
            "https://images.pexels.com/photos/1334597/pexels-photo-1334597.jpeg",
            "Tablet",
            "150"
        ));

        this.products.add(new Product(
            4,
            "Auricular Bluetooth Gamer G-Pulse",
            "Sonido Envolvente RGB",
            "Auriculares gamer con iluminación RGB.",
            "gamer-gpulse",
            "https://images.pexels.com/photos/7046709/pexels-photo-7046709.jpeg",
            "Auriculares",
            "50"
        ));

        this.products.add(new Product(
            5,
            "Altavoz Inteligente HomeSound Mini",
            "Control por Voz",
            "Altavoz inteligente compatible con asistentes virtuales.",
            "altavoz-homesound-mini",
            "https://images.pexels.com/photos/2651794/pexels-photo-2651794.jpeg",
            "Altavoz",
            "75"
        ));
    }

    public List<Product> getProducts() {
        return products;
    }

    

  

}

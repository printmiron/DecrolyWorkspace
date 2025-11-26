package com.decroly.backcarrito.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decroly.backcarrito.model.Product;
import com.decroly.backcarrito.model.Shop;

@RestController
@RequestMapping("/api")
public class CarritoController {

    @GetMapping("/productos")
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> products = new Shop().getProducts(); // ← obtenemos la lista

        return ResponseEntity.ok(products); // ← enviamos SOLO la lista
    }

}

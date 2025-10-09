package com.decroly.backcarrito.controller;

import com.decroly.backcarrito.model.Shop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarritoController {


    @GetMapping(value = "/carrito")
    public ResponseEntity<Shop> getCharacters() {
        return ResponseEntity.ok(new Shop("€"));
    }
}

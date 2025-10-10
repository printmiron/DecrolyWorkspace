package com.minitienda.minitienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class ControllerPractica {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hola!";
    }


    @GetMapping("/description")
    @ResponseBody
    public String description() {
        return "Bien venido a la mini tienda";
    }

    @GetMapping("/products")
    @ResponseBody
    public String listarProductos() {
    return "Producto 1: Iphone17, Producto 2: Ipad Pro, Producto 3: MacBook Air";
    }

    @GetMapping("/date")
    @ResponseBody
    public String mostrarFecha() {
    // LocalDate.now() devuelve la fecha actual.
    return "Fecha actual: " + java.time.LocalDate.now();
    }


}

package com.morosos.morosos_app.controller;

import com.morosos.morosos_app.model.Moroso; // Ajusta si tu package es otro

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;


//rest: esta clase maneja peticiones HTTP y responde con JSON
@RestController

//request define la ruta base de todos los endpoints en "/api/morosos"
@RequestMapping("/api/morosos")
public class MorosoController {
    
    //lista para Morosos
    private static final LinkedList<Moroso> LIST = new LinkedList<>();
    //generador de ids que empieza por 1 -> 2,3,4...
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);


    //lista completa de los morosos GET -------------------------------------



    //metodo responde a peticones "GET"
    @GetMapping
    //devuelve el codigo 200 y la lista completa de morosos, cunado postman hace GET http://localhost:8080/api/morosos api devuelve
    //todos los morosos
    //rsponseEntity: respresenta una respuesta HTTP completa con cuerpo, encabezados y el codigo de estado
    public ResponseEntity<LinkedList<Moroso>> getAll(){
        return ResponseEntity.ok(LIST);
    }

    //metodos para comprobar las respuestas
    
    //crear moroso POST -----------------------------------------------------

    //metodo responde a peticiones "POST"
    @PostMapping
    //recibe datos JSON enviados desde "postman -> cliente"
    public ResponseEntity<?> create(@RequestBody Moroso moroso){

        //hacemos que el nombre sea obligatorio, si no envia el "nombre" devulve 400 BAD REQUEST
        if (moroso.getNombre() == null || moroso.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio");
        }


        //si no envia un importe se pone por defecto a 0
        if (moroso.getImporte() == null) {
            moroso.setImporte(BigDecimal.ZERO);
        }

        //no permitir ingersos negativos, menos de 0
        if (moroso.getImporte().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().body("El importe no puede ser negativo");
        }

        //agignar una id automaticamente al moroso
        moroso.setId(ID_GENERATOR.getAndIncrement());

        //añadirle al final de la lista
        LIST.addLast(moroso);


        //deveulve codigo 201, que significa que se a creado bien
        return ResponseEntity.status(HttpStatus.CREATED).body(moroso);
    }



    //borrar/eliminar moroso POST -----------------------------------------------------


    //borra un moroso por id del URL
    @DeleteMapping("/{id}")
    //pathVariable: extrae el id de la ruta
    public ResponseEntity<Void> delete(@PathVariable Long id){

        //si encuantra el moroso -> 204 No Content. y si no existe -> 404 Not Found
        for (Moroso moroso : LIST) {
            if (moroso.getId().equals(id)) {
                LIST.remove(moroso);
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();

    }

}
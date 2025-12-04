package com.example.examen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.entity.Equipos;
import com.example.examen.services.EquiposService;

@RestController
@RequestMapping("/equipos")
public class EquiposController {

    @Autowired
    private EquiposService equiposService;

    //GET 
    @GetMapping
    public List<Equipos> getAll() {
        return equiposService.findAll();
    }

    //POST
    @PostMapping
    public ResponseEntity<Equipos> create(@RequestBody Equipos equipos) {
        equipos.setId(null);
        return ResponseEntity.ok(equiposService.save(equipos));
    }

    //PUT
    @PutMapping("/{id}/nombre")
    public ResponseEntity<Equipos> updateNombre(
            @PathVariable Long id,
            @RequestBody String nuevoEquipo) {

        Equipos actualizado = equiposService.updateNombre(id, nuevoEquipo);

        if (actualizado == null) {
            // Puede ser porque no existe o porque el estado es inválido
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!equiposService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        equiposService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

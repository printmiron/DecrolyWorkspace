package com.example.morosos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.morosos.models.Moroso;
import com.example.morosos.service.MorosoService;

import java.util.List;
@RestController
@RequestMapping("/morosos")
public class MorosoController {
 
    private final MorosoService morosoService;
 
    public MorosoController(MorosoService morosoService) {
        this.morosoService = morosoService;
    }
 
    // GET /morosos
    @GetMapping
    public ResponseEntity<List<Moroso>> getAll() {
        return ResponseEntity.ok(morosoService.findAll());
    }
 
    // GET /morosos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Moroso> getById(@PathVariable Long id) {
        Moroso moroso = morosoService.findById(id);
        if (moroso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moroso);
    }
 
    // POST /morosos
    @PostMapping
    public ResponseEntity<Moroso> create(@RequestBody Moroso moroso) {
        return ResponseEntity.ok(morosoService.save(moroso));
    }
 
    // PUT /morosos/{id}/estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<Moroso> updateEstado(
            @PathVariable Long id,
            @RequestBody String nuevoEstado) {
 
        Moroso actualizado = morosoService.updateEstado(id, nuevoEstado);
 
        if (actualizado == null) {
            // Puede ser porque no existe o porque el estado es inválido
            return ResponseEntity.badRequest().build();
        }
 
        return ResponseEntity.ok(actualizado);
    }
}

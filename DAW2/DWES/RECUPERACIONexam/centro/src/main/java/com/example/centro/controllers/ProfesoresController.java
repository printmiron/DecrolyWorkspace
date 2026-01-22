package com.example.centro.controllers;

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

import com.example.centro.entity.Profesores;
import com.example.centro.services.ProfesoresService;

@RestController
@RequestMapping("/profesores")
public class ProfesoresController {
    @Autowired
    private ProfesoresService profesoresService;

    // 1. Listar todos
    @GetMapping
    public List<Profesores> getAll() {
        return profesoresService.findAll();
    }

    //por id
    @GetMapping("/{id}")
    public ResponseEntity<Profesores> getById(@PathVariable String id) {
        Profesores profesores = profesoresService.findById(id);
        if (profesores == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesores);
    }

    // 3. Añadir 
    @PostMapping
    public ResponseEntity<Profesores> create(@RequestBody Profesores profesores) {
        profesores.setId(null);
        return ResponseEntity.ok(profesoresService.save(profesores));
    }

     // editar
    @PutMapping("/{id}")
    public ResponseEntity<Profesores> update(@PathVariable String id, @RequestBody Profesores profesoresDetalles) {
        Profesores profesor = profesoresService.findById(id);
        if (profesor == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizamos el equipo y guardamos
        profesor.setEspecialidad(profesoresDetalles.getEspecialidad());
        return ResponseEntity.ok(profesoresService.save(profesor));
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Profesores profesor = profesoresService.findById(id);
        if (profesor == null) {
            return ResponseEntity.notFound().build();
        }

        profesoresService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.centro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.centro.entity.Aula;
import com.example.centro.services.AulaService;

@RestController
@RequestMapping("/aulas")
public class AulaController {
    @Autowired
    private AulaService aulaService;

    // Listar todos
    @GetMapping
    public List<Aula> getAll() {
        return aulaService.findAll();
    }

    // 3. Añadir 
    @PostMapping
    public ResponseEntity<Aula> create(@RequestBody Aula aula) {
        aula.setId(null);
        return ResponseEntity.ok(aulaService.save(aula));
    }

    //por id
    @GetMapping("/{id}")
    public ResponseEntity<Aula> getById(@PathVariable String id) {
        Aula aula = aulaService.findById(id);
        if (aula == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aula);
    }
    
}

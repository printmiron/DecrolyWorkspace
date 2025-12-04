package com.example.examen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.Rol;
import com.example.examen.entity.Arbitros;
import com.example.examen.services.ArbitrosService;

@RestController
@RequestMapping("/arbitros")
public class ArbitrosController {

    @Autowired
    private ArbitrosService arbitrosService;

    @GetMapping
    public List<Arbitros> getAll() {
        return arbitrosService.findAll();
    }

    @PostMapping
    public ResponseEntity<Arbitros> create(@RequestBody Arbitros arbitros) {
        arbitros.setId(null);
        return ResponseEntity.ok(arbitrosService.save(arbitros));
    }

    //rol
    @GetMapping("/rol/{rol}")
    public List<Arbitros> getByCondicion(@PathVariable Rol rol) {
        return arbitrosService.findByRol(rol);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!arbitrosService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        arbitrosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

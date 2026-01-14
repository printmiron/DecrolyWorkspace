package com.example.futbol.controller;

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

import com.example.futbol.Rol;
import com.example.futbol.entity.Arbitros;
import com.example.futbol.service.ArbitrosService;

@RestController
@RequestMapping("/arbitros")
public class ArbitrosController {

    @Autowired
    private ArbitrosService arbitrosService;

    // Listar todos
    @GetMapping
    public List<Arbitros> getAll() {
        return arbitrosService.findAll();
    }

    @PostMapping
    public Arbitros create(@RequestBody Arbitros arbitros) {
        return arbitrosService.save(arbitros);
    }

    @GetMapping("/rol/{rol}")
    public List<Arbitros> getByRol(@PathVariable Rol rol) {
        return arbitrosService.findByRol(rol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Arbitros arbitros = arbitrosService.findById(id);
        if (arbitros == null) {
            return ResponseEntity.notFound().build();
        }

        arbitrosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

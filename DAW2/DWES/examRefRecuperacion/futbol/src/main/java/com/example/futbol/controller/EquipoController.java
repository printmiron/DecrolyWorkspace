package com.example.futbol.controller;

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

import com.example.futbol.entity.Equipo;
import com.example.futbol.service.EquipoService;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // Listar todos
    @GetMapping
    public List<Equipo> getAll() {
        return equipoService.findAll();
    }

    // Añadir equipo
    @PostMapping
    public Equipo create(@RequestBody Equipo equipo) {
        return equipoService.save(equipo);
    }

    // Modificar nombre (u otros datos)
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> update(@PathVariable Long id, @RequestBody Equipo detalles) {
        Equipo equipo = equipoService.findById(id);
        if (equipo == null) {
            return ResponseEntity.notFound().build();
        }

        equipo.setNombreEquipo(detalles.getNombreEquipo());
        equipo.setSede(detalles.getSede());

        return ResponseEntity.ok(equipoService.save(equipo));
    }

    // Borrar equipo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        if (equipo == null) {
            return ResponseEntity.notFound().build();
        }

        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

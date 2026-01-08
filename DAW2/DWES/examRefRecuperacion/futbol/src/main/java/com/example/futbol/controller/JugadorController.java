package com.example.futbol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.futbol.entity.Jugador;
import com.example.futbol.service.JugadorService;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // 1. Listar todos los jugadores
    @GetMapping
    public List<Jugador> getAll() {
        return jugadorService.findAll();
    }

    // 2. Listar jugadores por nombre de equipo
    // Ejemplo: /jugadores/equipo/RealMadrid
    @GetMapping("/equipo/{nombreEquipo}")
    public List<Jugador> getByEquipo(@PathVariable String nombreEquipo) {
        return jugadorService.findByNombreEquipo(nombreEquipo);
    }

    // 3. Añadir jugadores
    @PostMapping
    public ResponseEntity<Jugador> create(@RequestBody Jugador jugador) {
        jugador.setId(null);
        return ResponseEntity.ok(jugadorService.save(jugador));
    }

    // 4. Editar el equipo (o datos) de un jugador
    // Ejemplo: PUT /jugadores/5
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> update(@PathVariable String id, @RequestBody Jugador jugadorDetalles) {
        Jugador jugador = jugadorService.findById(id);
        if (jugador == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizamos el equipo y guardamos
        jugador.setEquipo(jugadorDetalles.getEquipo());
        return ResponseEntity.ok(jugadorService.save(jugador));
    }

}

package com.example.examen.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.entity.Equipos;
import com.example.examen.entity.Jugadores;
import com.example.examen.repository.EquiposRepository;
import com.example.examen.repository.JugadoresRepository;
import com.example.examen.services.JugadoresService;

@RestController
@RequestMapping("/jugadores")
public class JugadoresController {

    private final EquiposRepository equiposRepository;
    private final JugadoresRepository jugadoresRepository;
    private final JugadoresService jugadoresService;

    public JugadoresController(EquiposRepository equiposRepository,
            JugadoresRepository jugadoresRepository,
            JugadoresService jugadoresService) {
        this.equiposRepository = equiposRepository;
        this.jugadoresRepository = jugadoresRepository;
        this.jugadoresService = jugadoresService;
    }

    //GET
    @GetMapping
    public List<Jugadores> getAll() {
        return jugadoresService.findAll();
    }

     @GetMapping("/equipos/{equiposId}/jugadores")
     public ResponseEntity<List<Jugadores>> getJugadoresPorEquipo(@PathVariable Long equiposId) {

        
       

         if (!equiposRepository.existsById(equiposId)) {
             return ResponseEntity.notFound().build();
         }

         List<Jugadores> jugadores = jugadoresRepository.findByEquiposId(equiposId);
         return ResponseEntity.ok(jugadores);
    }

    //POST
    @PostMapping
    public ResponseEntity<Jugadores> crearJugador(@RequestBody Jugadores jugadores
    ) {
        // Validar que el país exista

        if (jugadores.getEquipos() == null) {
            return ResponseEntity.badRequest().build();
        }

        Long equipoID = jugadores.getEquipos().getId();
        Equipos equipos = equiposRepository.findById(equipoID).orElse(null);
        if (equipos == null) {
            return ResponseEntity.badRequest().build();
        }

        jugadores.setEquipos(equipos);

        // Guardar ciudad
        Jugadores jugadorGuardado = jugadoresService.save(jugadores);

        URI location = URI.create("/jugadores/" + jugadorGuardado.getId());
        return ResponseEntity.created(location).body(jugadorGuardado);
    }

    //PUT
    // @PutMapping("/{id}/equipos")
    // public ResponseEntity<Equipos> updateEquipo(
    //         @PathVariable String id,
    //         @RequestBody String nuevoEquipo) {
    //     Jugadores actualizado = jugadoresService.updateEquipo(id, nuevoEquipo);
    //     if (actualizado == null) {
    //         // Puede ser porque no existe o porque el estado es inválido
    //         return ResponseEntity.badRequest().build();
    //     }
    //     return ResponseEntity.ok(actualizado);
    // }
}

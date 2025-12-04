package com.example.examen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.examen.entity.Jugadores;
import com.example.examen.repository.JugadoresRepository;

@Service
public class JugadoresService {

    private final JugadoresRepository jugadoresRepository;

    public JugadoresService(JugadoresRepository jugadoresRepository) {
        this.jugadoresRepository = jugadoresRepository;
    }

    // GET ALL
    public List<Jugadores> findAll() {
        return jugadoresRepository.findAll();
    }

    // POST
    public Jugadores save(Jugadores jugadores) {
        return jugadoresRepository.save(jugadores);
    }

    // //PUT Equipo
    // public Jugadores updateEquipo(String id, Long nuevoEquipo) {
    //     if (nuevoEquipo == null) {
    //         return null;
    //     }

    //     Long equipoNormal = nuevoEquipo;

    //     if (!equipoNormal.equals(nuevoEquipo)) {
    //         return null;
    //     }

    //     Jugadores jugadores = jugadoresRepository.findById(id).orElse(null);
    //     if (jugadores == null) {
    //         return null;
    //     }

    //     jugadores.setEquipos(nuevoEquipo);
    //     return jugadoresRepository.save(jugadores);
    // }
}

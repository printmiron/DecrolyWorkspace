package com.example.futbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.futbol.entity.Jugador;
import com.example.futbol.repository.JugadorRepository;



@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    // Listar todos
    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    // Guardar o actualizar
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    // Buscar por ID (para poder editar)
    public Jugador findById(String id) {
        return jugadorRepository.findById(id).orElse(null);
    }

    // Buscar por nombre de equipo
    public List<Jugador> findByNombreEquipo(String nombreEquipo) {
        return jugadorRepository.findByEquipo(nombreEquipo);
    }
}

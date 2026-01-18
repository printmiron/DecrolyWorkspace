package com.example.futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.futbol.entity.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, String> {
    
    
    List<Jugador> findByEquipoNombreEquipo(String nombre);
}
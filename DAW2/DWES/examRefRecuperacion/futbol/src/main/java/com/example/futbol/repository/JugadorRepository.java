package com.example.futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.futbol.entity.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, String> {
    
    // CAMBIA findByEquipo por esto:
    // Al poner "EquipoNombreEquipo" le decimos a Spring: 
    // "Busca dentro del objeto Equipo el atributo nombreEquipo que es un String"
    List<Jugador> findByEquipoNombreEquipo(String nombre);
}
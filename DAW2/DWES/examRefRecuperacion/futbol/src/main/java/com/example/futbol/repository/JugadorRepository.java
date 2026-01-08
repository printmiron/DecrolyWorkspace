package com.example.futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.futbol.entity.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, String> {

    // Este método permite filtrar por el campo "equipo" de tu entidad Jugador
    // Spring Data JPA crea la consulta SQL automáticamente
    List<Jugador> findByEquipo(String nombre);
    

}

package com.example.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Jugadores;

@Repository
public interface  JugadoresRepository extends JpaRepository<Jugadores, String>{
     //metodo para buscar jugadores por el id del equipo
     List<Jugadores> findByEquiposId(Long equiposId);

    

    
}

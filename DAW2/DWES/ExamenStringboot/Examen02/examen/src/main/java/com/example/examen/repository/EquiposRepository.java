package com.example.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Equipos;

@Repository
public interface EquiposRepository extends JpaRepository<Equipos, Long> {
   
}

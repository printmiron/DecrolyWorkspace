package com.example.futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.futbol.entity.Equipo;


@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    
}

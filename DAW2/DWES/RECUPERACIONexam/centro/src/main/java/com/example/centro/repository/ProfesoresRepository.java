package com.example.centro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.centro.entity.Profesores;

@Repository
public interface ProfesoresRepository extends JpaRepository<Profesores, String>{
    
}

package com.example.centro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.centro.entity.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, String>{
    
}

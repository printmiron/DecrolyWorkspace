package com.example.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.Rol;
import com.example.examen.entity.Arbitros;

@Repository
public interface ArbitrosRepository extends JpaRepository<Arbitros, String> {

    List<Arbitros> findByRol(Rol rol);
}

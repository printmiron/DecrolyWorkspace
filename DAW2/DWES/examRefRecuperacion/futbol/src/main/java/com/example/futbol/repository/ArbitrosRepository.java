package com.example.futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.futbol.Rol;
import com.example.futbol.entity.Arbitros;

@Repository
public interface ArbitrosRepository extends JpaRepository<Arbitros, String> {
    List<Arbitros> findByRol(Rol rol);
}

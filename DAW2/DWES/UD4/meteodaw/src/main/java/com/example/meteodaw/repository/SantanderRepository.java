package com.example.meteodaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.meteodaw.entity.Santander;

import java.sql.Date;
import java.util.List;
import com.example.meteodaw.Condicion;


@Repository
public interface SantanderRepository extends JpaRepository<Santander, Long>{
    List<Santander> findByCondicion(Condicion condicion);

    List<Santander> findByFecha(Date fecha);
    
} 
package com.example.meteodaw.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.meteodaw.Condicion;
import com.example.meteodaw.entity.Santander;
import com.example.meteodaw.entity.Toledo;

@Repository
public interface ToledoRepository extends JpaRepository<Toledo, Long>{
    List<Toledo> findByCondicion(Condicion condicion);

    List<Toledo> findByFecha(Date fecha);
    
} 
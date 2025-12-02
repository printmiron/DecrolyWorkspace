package com.example.meteodaw.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.meteodaw.Condicion;
import com.example.meteodaw.entity.Santander;
import com.example.meteodaw.entity.Torremolinos;

@Repository
public interface TorremolinosRepository extends JpaRepository<Torremolinos, Long>{

        List<Torremolinos> findByCondicion(Condicion condicion);

    List<Torremolinos> findByFecha(Date fecha);
} 
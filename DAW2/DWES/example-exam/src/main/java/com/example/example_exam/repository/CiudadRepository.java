package com.example.example_exam.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.example_exam.Condicion;
import com.example.example_exam.entity.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    // Método para buscar ciudades por el id del país
    List<Ciudad> findByPaisId(Long paisId);

    List<Ciudad> findByCondicion(Condicion condicion);

    List<Ciudad> findByFecha(Date fecha);

}

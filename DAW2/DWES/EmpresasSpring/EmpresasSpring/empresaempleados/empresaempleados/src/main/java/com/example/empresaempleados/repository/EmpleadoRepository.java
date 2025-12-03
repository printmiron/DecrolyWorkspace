package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Spring Data construye la query automáticamente
    List<Empleado> findByEmpresaId(Long empresaId);
}

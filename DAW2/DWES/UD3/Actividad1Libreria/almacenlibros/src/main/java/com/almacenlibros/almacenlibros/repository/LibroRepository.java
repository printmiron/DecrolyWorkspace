package com.almacenlibros.almacenlibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almacenlibros.almacenlibros.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}

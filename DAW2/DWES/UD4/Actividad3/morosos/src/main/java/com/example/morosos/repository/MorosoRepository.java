package com.example.morosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.morosos.models.Moroso;

@Repository
public interface MorosoRepository extends JpaRepository<Moroso, Long> {
}

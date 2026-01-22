package com.example.centro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.centro.entity.Aula;
import com.example.centro.repository.AulaRepository;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    public List<Aula> findAll() {
        return aulaRepository.findAll();
    }

    public Aula findById(String id) {
        return aulaRepository.findById(id).orElse(null);
    }

    // Guardar o actualizar
    public Aula save(Aula aula) {
        return aulaRepository.save(aula);
    }
}

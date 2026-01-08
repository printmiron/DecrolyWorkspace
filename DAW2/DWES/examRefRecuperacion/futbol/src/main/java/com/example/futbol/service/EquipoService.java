package com.example.futbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.futbol.entity.Equipo;
import com.example.futbol.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo findById(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }
}

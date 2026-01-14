package com.example.futbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.futbol.Rol;
import com.example.futbol.entity.Arbitros;
import com.example.futbol.repository.ArbitrosRepository;

@Service
public class ArbitrosService {

    @Autowired
    private ArbitrosRepository arbitrosRepository;

    // Listar todos
    public List<Arbitros> findAll() {
        return arbitrosRepository.findAll();
    }

    public Arbitros save(Arbitros arbitros) {
        return arbitrosRepository.save(arbitros);
    }

    public List<Arbitros> findByRol(Rol rol) {
        return arbitrosRepository.findByRol(rol);
    }

    public Arbitros findById(String id) { 
        return arbitrosRepository.findById(id).orElse(null);
    }

    public void delete(String id) {
        arbitrosRepository.deleteById(id);
    }
}

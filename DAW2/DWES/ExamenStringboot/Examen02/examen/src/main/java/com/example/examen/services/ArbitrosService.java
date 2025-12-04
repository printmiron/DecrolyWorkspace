package com.example.examen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.examen.Rol;
import com.example.examen.entity.Arbitros;
import com.example.examen.repository.ArbitrosRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ArbitrosService {

    private final ArbitrosRepository arbitrosRepository;

    public ArbitrosService(ArbitrosRepository arbitrosRepository) {
        this.arbitrosRepository = arbitrosRepository;
    }

    // GET ALL
    public List<Arbitros> findAll() {
        return arbitrosRepository.findAll();
    }

    // POST
    public Arbitros save(Arbitros arbitros) {
        return arbitrosRepository.save(arbitros);
    }

    //GET por ROL
    public List<Arbitros> findByRol(Rol rol) {
        return arbitrosRepository.findByRol(rol);
    }

     // Verificar existencia
    public boolean existsById(String id) {
        return arbitrosRepository.existsById(id);
    }

    // DELETE
    public void deleteById(String id) {
        if (arbitrosRepository.existsById(id)) {
            arbitrosRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Equipo con id " + id + " no existe");
        }
    }

}

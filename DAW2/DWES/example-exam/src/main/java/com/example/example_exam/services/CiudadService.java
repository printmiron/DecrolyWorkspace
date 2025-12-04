package com.example.example_exam.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.example_exam.Condicion;
import com.example.example_exam.entity.Ciudad;
import com.example.example_exam.repository.CiudadRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    // GET ALL
    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }

    // GET por condición
    public List<Ciudad> findByCondiciones(Condicion condicion) {
        return ciudadRepository.findByCondicion(condicion);
    }

    // GET por fecha
    public List<Ciudad> findByFecha(Date fecha) {
        return ciudadRepository.findByFecha(fecha);
    }

    // POST
    public Ciudad save(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    // PUT actualizar condición
    public Ciudad updateCondicion(String id, String nuevoCondicion) {
        if (nuevoCondicion == null) return null;

        String condicionNormal = nuevoCondicion.trim().toUpperCase();

        if (!condicionNormal.equals("LLUVIA") && !condicionNormal.equals("SOLEADO")
                && !condicionNormal.equals("NUBLADO") && !condicionNormal.equals("TORMENTA")
                && !condicionNormal.equals("NIEVE") && !condicionNormal.equals("GRANIZO")
                && !condicionNormal.equals("GALERNA")) {
            return null;
        }

        Ciudad ciudad = ciudadRepository.findById(id).orElse(null);
        if (ciudad == null) return null;

        ciudad.setCondicion(Condicion.valueOf(condicionNormal));
        return ciudadRepository.save(ciudad);
    }

    // Verificar existencia
    public boolean existsById(String id) {
        return ciudadRepository.existsById(id);
    }

    // DELETE
    public void deleteById(String id) {
        if (ciudadRepository.existsById(id)) {
            ciudadRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Ciudad con id " + id + " no existe");
        }
    }
}

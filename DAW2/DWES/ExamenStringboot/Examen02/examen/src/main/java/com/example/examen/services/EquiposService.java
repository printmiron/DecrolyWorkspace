package com.example.examen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.examen.entity.Equipos;
import com.example.examen.repository.EquiposRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquiposService {

    private final EquiposRepository equiposRepository;

    public EquiposService(EquiposRepository equiposRepository) {
        this.equiposRepository = equiposRepository;
    }

    // GET ALL
    public List<Equipos> findAll() {
        return equiposRepository.findAll();
    }

    // POST
    public Equipos save(Equipos equipos) {
        return equiposRepository.save(equipos);
    }

   
   
    //PUT NOMBRE
    public Equipos updateNombre(Long id, String nuevoNombre) {
        if (nuevoNombre == null) {
            return null;
        }

        String nombreNormal = nuevoNombre.trim().toUpperCase();

        if (!nombreNormal.equals(nuevoNombre)) {
            return null;
        }

        Equipos equipos = equiposRepository.findById(id).orElse(null);
        if (equipos == null) {
            return null;
        }

        equipos.setNombreEquipo(nuevoNombre);
        return equiposRepository.save(equipos);
    }

    // Verificar existencia
    public boolean existsById(Long id) {
        return equiposRepository.existsById(id);
    }

    // DELETE
    public void deleteById(Long id) {
        if (equiposRepository.existsById(id)) {
            equiposRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Equipo con id " + id + " no existe");
        }
    }

}

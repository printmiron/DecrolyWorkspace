package com.example.meteodaw.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.meteodaw.Condicion;
import com.example.meteodaw.entity.Toledo;
import com.example.meteodaw.entity.Torremolinos;
import com.example.meteodaw.repository.ToledoRepository;
import com.example.meteodaw.repository.TorremolinosRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TorremolinosService {

    private final TorremolinosRepository TorremolinosRepository;

    public TorremolinosService(TorremolinosRepository TorremolinosRepository) {
        this.TorremolinosRepository = TorremolinosRepository;
    }

    // GET ALL

    public List<Torremolinos> findAll() {
        return TorremolinosRepository.findAll();
    }

    // GET CONDICION
    public List<Torremolinos> findByCondiciones(Condicion Condicion) {
        return TorremolinosRepository.findByCondicion(Condicion);
    }

    // GET FECHA
    public List<Torremolinos> findByFecha(Date fecha) {
        return TorremolinosRepository.findByFecha(fecha);
    }

    // POST
    public Torremolinos save(Torremolinos torremolinos) {
        return TorremolinosRepository.save(torremolinos);
    }

    // PUT ESTADO
    public Torremolinos updateCondicion(Long id, String nuevoCondicion) {
        if (nuevoCondicion == null) {
            return null;
        }

        String condicionNormal = nuevoCondicion.trim().toUpperCase();

        if (!condicionNormal.equals("LLUVIA") && !condicionNormal.equals("SOLEADO")
                && !condicionNormal.equals("NUBLADO") && !condicionNormal.equals("TORMENTA")
                && !condicionNormal.equals("NIEVE") && !condicionNormal.equals("GRANIZO")
                && !condicionNormal.equals("GALERNA")) {
            return null;
        }

        Torremolinos Torremolinos = TorremolinosRepository.findById(id).orElse(null);

        Torremolinos.setCondicion(Condicion.valueOf(condicionNormal));

        return TorremolinosRepository.save(Torremolinos);
    }

    public boolean existsById(Long id) {
        return TorremolinosRepository.findById(id) != null;
    }

    public void deleteById(Long id) {
        if (TorremolinosRepository.existsById(id)) {
            TorremolinosRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Torremolinos con id " + id + " no existe");
        }
    }
}

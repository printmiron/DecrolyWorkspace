package com.example.morosos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.morosos.Estado;
import com.example.morosos.models.Moroso;
import com.example.morosos.repository.MorosoRepository;

@Service
public class MorosoService {

    private final MorosoRepository morosoRepository;

    public MorosoService(MorosoRepository morosoRepository) {
        this.morosoRepository = morosoRepository;
    }

// GET /morosos
    public List<Moroso> findAll() {
        return morosoRepository.findAll();
    }

// GET /morosos/{id}
    public Moroso findById(Long id) {
        return morosoRepository.findById(id).orElse(null);
    }

// POST /morosos
    public Moroso save(Moroso moroso) {
        return morosoRepository.save(moroso);
    }

// PUT /morosos/{id}/estado -> actualizar solo el estado de pago
    public Moroso updateEstado(Long id, String nuevoEstado) {

        if (nuevoEstado == null) {
            return null;
        }

// Normalizamos el estado
        String estadoNormalizado = nuevoEstado.trim().toUpperCase();

// Comprobamos estados válidos
        if (!estadoNormalizado.equals("PENDIENTE") && !estadoNormalizado.equals("PAGADO")) {
            return null;
        }

// Buscar moroso
        Moroso moroso = morosoRepository.findById(id).orElse(null);

        if (moroso == null) {
            return null;
        }

// Actualizar campo estado_pago
        moroso.setEstadoPago(Estado.valueOf(estadoNormalizado));

        return morosoRepository.save(moroso);
    }
}

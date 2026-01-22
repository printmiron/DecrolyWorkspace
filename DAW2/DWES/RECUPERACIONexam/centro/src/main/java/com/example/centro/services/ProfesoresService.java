package com.example.centro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.centro.entity.Profesores;
import com.example.centro.repository.ProfesoresRepository;

@Service
public class ProfesoresService {

    @Autowired
    private ProfesoresRepository profesoresRepository;

    // Listar todos
    public List<Profesores> findAll() {
        return profesoresRepository.findAll();
    }

    // Buscar por ID (para poder editar)
    public Profesores findById(String id) {
        return profesoresRepository.findById(id).orElse(null);
    }

    // Guardar o actualizar
    public Profesores save(Profesores profesores) {
        return profesoresRepository.save(profesores);
    }

    public void delete(String id) {
        profesoresRepository.deleteById(id);
    }
}

package com.almacenlibros.almacenlibros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almacenlibros.almacenlibros.model.Libro;
import com.almacenlibros.almacenlibros.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro alquilarLibro(Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            Libro libroExistente = libro.get();
            libroExistente.setAlquilado(true);
            return libroRepository.save(libroExistente);
        }
        return null;
    }
}

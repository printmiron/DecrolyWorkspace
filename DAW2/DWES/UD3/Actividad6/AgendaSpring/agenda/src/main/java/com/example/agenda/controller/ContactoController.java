package com.example.agenda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agenda.model.Contacto;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    // Almacenamiento en un ArrayList
    private static final List<Contacto> CONTACTOS = new ArrayList<>();

    // Contenido mínimo para que podáis realizar las comprobaciones
    static {
        CONTACTOS.add(new Contacto(1L, "Ana López", "600111222", "ana@example.com", "trabajo", "Cliente potencial"));
        CONTACTOS.add(new Contacto(2L, "Luis Pérez", "600333444", "luis@example.com", "familia", "Primo de Madrid"));
    }


    @GetMapping
    public List<Contacto> listar() {
        return CONTACTOS;
    }

    // 200 si existe, 404 si no
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        int idx = indexOfId(id);
        if (idx == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el contacto con id=" + id);
        }
        return ResponseEntity.ok(CONTACTOS.get(idx));
    }

    // crear contacto
    // Normas: id obligatorio (mayor que 0 y único) y nombre obligatorio (no vacío)
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Contacto body) {
        if (body == null || body.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'id' es obligatorio.");
        }
        if (body.getId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El 'id' debe ser mayor que 0.");
        }
        if (body.getNombre() == null || body.getNombre().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'nombre' es obligatorio.");
        }
        if (indexOfId(body.getId()) != -1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un contacto con id=" + body.getId());
        }

        Contacto nuevo = new Contacto(
                body.getId(),
                body.getNombre().trim(),
                body.getTelefono(),
                body.getEmail(),
                body.getEtiqueta(),
                body.getNotas()
        );
        CONTACTOS.add(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // 204 si elimina, 404 si no existe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        int idx = indexOfId(id);
        if (idx == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CONTACTOS.remove(idx);
        return ResponseEntity.noContent().build();
    }

    // Buscador de contactos por id
    private int indexOfId(Long id) {
        for (int i = 0; i < CONTACTOS.size(); i++) {
            Contacto c = CONTACTOS.get(i);
            if (c != null && id.equals(c.getId())) return i;
        }
        return -1;
    }
}

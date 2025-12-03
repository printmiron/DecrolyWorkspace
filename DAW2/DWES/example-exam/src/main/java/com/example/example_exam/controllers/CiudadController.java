package com.example.example_exam.controllers;

import java.net.URI;
import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.example_exam.Condicion;
import com.example.example_exam.entity.Ciudad;
import com.example.example_exam.entity.Pais;
import com.example.example_exam.repository.CiudadRepository;
import com.example.example_exam.repository.PaisRepository;
import com.example.example_exam.services.CiudadService;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    private final PaisRepository paisRepository;
    private final CiudadRepository ciudadRepository;
    private final CiudadService ciudadService;

    public CiudadController(PaisRepository paisRepository,
            CiudadRepository ciudadRepository,
            CiudadService ciudadService) {
        this.paisRepository = paisRepository;
        this.ciudadRepository = ciudadRepository;
        this.ciudadService = ciudadService;
    }

    // 1) GET todas las ciudades
    @GetMapping
    public List<Ciudad> getAll() {
        return ciudadService.findAll();
    }

    // 2) GET ciudad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getCiudadPorId(@PathVariable Long id) {
        return ciudadRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3) GET ciudades por país
    @GetMapping("/paises/{paisId}/ciudades")
    public ResponseEntity<List<Ciudad>> getCiudadesPorPais(@PathVariable Long paisId) {
        if (!paisRepository.existsById(paisId)) {
            return ResponseEntity.notFound().build();
        }

        List<Ciudad> ciudades = ciudadRepository.findByPaisId(paisId);
        return ResponseEntity.ok(ciudades);
    }

    // 4) GET ciudades por condición
    @GetMapping("/condicion/{condicion}")
    public List<Ciudad> getByCondicion(@PathVariable Condicion condicion) {
        return ciudadService.findByCondiciones(condicion);
    }

    // 5) GET ciudades por fecha
    @GetMapping("/fecha/{fecha}")
    public List<Ciudad> getByFecha(@PathVariable Date fecha) {
        return ciudadService.findByFecha(fecha);
    }

    // 6) POST para crear ciudad
    //
    //    Ejemplo de JSON:
    //  {
    //   "nombre": "Santander",
    //   "fecha": "2025-12-03",
    //   "maxTemp": 30.5,
    //   "minTemp": 15.2,
    //   "mediaTemp": 22.8,
    //   "condicion": "ACTIVA",
    //   "humedad": 70,
    //   "velocidadV": 10,
    //   "pais": {"id": 1}
    //  }
    @PostMapping
    public ResponseEntity<Ciudad> crearCiudad(@RequestBody Ciudad ciudad) {
        // Validar que el país exista
        if (ciudad.getPais() == null || ciudad.getPais().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Long paisId = ciudad.getPais().getId();
        Pais pais = paisRepository.findById(paisId).orElse(null);
        if (pais == null) {
            return ResponseEntity.badRequest().build();
        }

        ciudad.setPais(pais);

        // Guardar ciudad
        Ciudad ciudadGuardada = ciudadService.save(ciudad);

        URI location = URI.create("/ciudad/" + ciudadGuardada.getId());
        return ResponseEntity.created(location).body(ciudadGuardada);
    }

    // 7) PUT para actualizar condición de ciudad
    @PutMapping("/{id}/condicion")
    public ResponseEntity<Ciudad> updateCondicion(
            @PathVariable Long id,
            @RequestBody String nuevoCondicion) {

        Ciudad actualizado = ciudadService.updateCondicion(id, nuevoCondicion);

        if (actualizado == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    // 8) DELETE ciudad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!ciudadService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        ciudadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



// 1️ ------------------------ POST → Crear una ciudad

// Endpoint: POST /ciudad
// Body (raw JSON):

// {
//   "nombre": "Madrid",
//   "pais": {
//     "id": 1
//   },
//   "fecha": "2025-12-03",
//   "maxTemp": 30.5,
//   "minTemp": 15.2,
//   "mediaTemp": 22.8,
//   "condicion": "ACTIVA",
//   "humedad": 70,
//   "velocidadV": 10
// }


//Devuelve 201 Created con la ciudad creada.





// 2 ------------------------------ GET → Obtener todas las ciudades

// Endpoint: GET /ciudad

// No necesita body.
// Devuelve lista de ciudades.





// 3 ------------------------------------- GET → Obtener ciudad por ID

// Endpoint: GET /ciudad/{id}

// Ejemplo: GET /ciudad/1

// Devuelve la ciudad con ID 1 o 404 si no existe.





// 4 ------------------------------------ GET → Obtener ciudades por país





// Endpoint: GET /ciudad/paises/{paisId}/ciudades

// Ejemplo: GET /ciudad/paises/1/ciudades

// Devuelve lista de ciudades asociadas al país con ID 1.






// 5 --------------------------------------- GET → Obtener ciudades por condición

// Endpoint: GET /ciudad/condicion/{condicion}

// Ejemplo: GET /ciudad/condicion/ACTIVA

// Devuelve todas las ciudades con condicion = ACTIVA.





// 6 ------------------------------------- GET → Obtener ciudades por fecha

// Endpoint: GET /ciudad/fecha/{fecha}

// Ejemplo: GET /ciudad/fecha/2025-12-03

// Devuelve todas las ciudades cuya fecha coincide.





// 7 ------------------------------------- PUT → Actualizar condición de una ciudad

// Endpoint: PUT /ciudad/{id}/condicion
// Body (raw JSON):

// "INACTIVA"


// Ejemplo: PUT /ciudad/1/condicion
// Cambia la condición de la ciudad con ID 1 a INACTIVA.





// 8 ------------------------------------ DELETE → Eliminar ciudad por ID

// Endpoint: DELETE /ciudad/{id}

// Ejemplo: DELETE /ciudad/1

// Devuelve 204 No Content si la ciudad se eliminó correctamente.

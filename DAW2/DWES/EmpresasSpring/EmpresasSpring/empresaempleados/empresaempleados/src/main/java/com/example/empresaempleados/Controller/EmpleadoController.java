package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Empleado;
import com.example.demo.entity.Empresa;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.EmpresaRepository;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;

    public EmpleadoController(EmpleadoRepository empleadoRepository,
                              EmpresaRepository empresaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.empresaRepository = empresaRepository;
    }

    // 1) GET empleados de una empresa por ID de empresa
    //    Ejemplo: GET /api/empresas/1/empleados
    @GetMapping("/empresas/{empresaId}/empleados")
    public ResponseEntity<List<Empleado>> getEmpleadosPorEmpresa(@PathVariable Long empresaId) {

        // Comprobamos que la empresa exista
        if (!empresaRepository.existsById(empresaId)) {
            return ResponseEntity.notFound().build();
        }

        List<Empleado> empleados = empleadoRepository.findByEmpresaId(empresaId);
        return ResponseEntity.ok(empleados);
    }

    // 2) GET empleado por ID de empleado
    //    Ejemplo: GET /api/empleados/1
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable Long id) {
        return empleadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3) POST para crear un empleado
    //    Ejemplo de JSON:
    //    {
    //      "nombre": "Juan",
    //      "apellido": "Pérez",
    //      "edad": 30,
    //      "empresa": { "id": 1 }
    //    }
    @PostMapping("/empleados")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {

        // Validamos que la empresa exista y la cargamos completa
        if (empleado.getEmpresa() == null || empleado.getEmpresa().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Long empresaId = empleado.getEmpresa().getId();

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElse(null);

        if (empresa == null) {
            return ResponseEntity.badRequest().build();
        }

        // Asociamos la empresa "real" al empleado
        empleado.setEmpresa(empresa);

        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        // Devolvemos 201 Created con la URL del nuevo recurso
        URI location = URI.create("/api/empleados/" + empleadoGuardado.getId());
        return ResponseEntity.created(location).body(empleadoGuardado);
    }
}

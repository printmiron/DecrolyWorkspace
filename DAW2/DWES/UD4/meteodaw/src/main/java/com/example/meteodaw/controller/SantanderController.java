package com.example.meteodaw.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meteodaw.Condicion;
import com.example.meteodaw.entity.Santander;
import com.example.meteodaw.entity.Toledo;
import com.example.meteodaw.service.SantanderService;
import com.example.meteodaw.service.ToledoService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/santander")
public class SantanderController {
      @Autowired
    private SantanderService santanderService;



    @GetMapping
    public List<Santander> getAll() {
        return santanderService.findAll();
    }

    //condicion
    @GetMapping("/condicion/{condicion}")
    public List<Santander> getByCondicion(@PathVariable Condicion condicion) {
        return santanderService.findByCondiciones(condicion);
    }

    //fecha
    @GetMapping("/fecha/{fecha}")
    public List<Santander> getByFecha(@PathVariable Date fecha) {
        return santanderService.findByFecha(fecha);
    }
    
    

    @PostMapping
    public ResponseEntity<Santander> create(@RequestBody Santander santander) {
        santander.setId(null);
        return ResponseEntity.ok(santanderService.save(santander));
        //Toledo guardado = toledoService.save(toledo);
    //     return ResponseEntity
    //             .created(URI.create("/toledo/" + guardado.getId()))
    //             .body(guardado);
    // }
    }

    @PutMapping("/{id}/condicion")
    public ResponseEntity<Santander> updateCondicion(
            @PathVariable Long id,
            @RequestBody String nuevoCondicion) {
 
        Santander actualizado = santanderService.updateCondicion(id, nuevoCondicion);
 
        if (actualizado == null) {
            // Puede ser porque no existe o porque el estado es inválido
            return ResponseEntity.badRequest().build();
        }
 
        return ResponseEntity.ok(actualizado);
    }
    


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!santanderService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        santanderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

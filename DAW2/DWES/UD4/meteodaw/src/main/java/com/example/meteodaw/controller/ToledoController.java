package com.example.meteodaw.controller;

import java.net.URI;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meteodaw.Condicion;
import com.example.meteodaw.entity.Santander;
import com.example.meteodaw.entity.Toledo;
import com.example.meteodaw.repository.ToledoRepository;
import com.example.meteodaw.service.ToledoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/toledo")
public class ToledoController {

    @Autowired
    private ToledoService toledoService;



    @GetMapping
    public List<Toledo> getAll() {
        return toledoService.findAll();
    }

        //condicion
    @GetMapping("/condicion/{condicion}")
    public List<Toledo> getByCondicion(@PathVariable Condicion condicion) {
        return toledoService.findByCondiciones(condicion);
    }

    //fecha
    @GetMapping("/fecha/{fecha}")
    public List<Toledo> getByFecha(@PathVariable Date fecha) {
        return toledoService.findByFecha(fecha);
    }

    @PostMapping
    public ResponseEntity<Toledo> create(@RequestBody Toledo toledo) {
        toledo.setId(null);
        return ResponseEntity.ok(toledoService.save(toledo));
        //Toledo guardado = toledoService.save(toledo);
    //     return ResponseEntity
    //             .created(URI.create("/toledo/" + guardado.getId()))
    //             .body(guardado);
    // }
    }

    @PutMapping("/{id}/condicion")
    public ResponseEntity<Toledo> updateCondicion(
            @PathVariable Long id,
            @RequestBody String nuevoCondicion) {
 
        Toledo actualizado = toledoService.updateCondicion(id, nuevoCondicion);
 
        if (actualizado == null) {
            // Puede ser porque no existe o porque el estado es inválido
            return ResponseEntity.badRequest().build();
        }
 
        return ResponseEntity.ok(actualizado);
    }
    


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!toledoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        toledoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

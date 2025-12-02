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
import com.example.meteodaw.entity.Toledo;
import com.example.meteodaw.entity.Torremolinos;
import com.example.meteodaw.entity.Torremolinos;
import com.example.meteodaw.service.TorremolinosService;
import com.example.meteodaw.service.TorremolinosService;

@RestController
@RequestMapping("/torremolinos")
public class TorremolinosController {
    @Autowired
    private TorremolinosService TorremolinosService;



    @GetMapping
    public List<Torremolinos> getAll() {
        return TorremolinosService.findAll();
    }

        //condicion
    @GetMapping("/condicion/{condicion}")
    public List<Torremolinos> getByCondicion(@PathVariable Condicion condicion) {
        return TorremolinosService.findByCondiciones(condicion);
    }

    //fecha
    @GetMapping("/fecha/{fecha}")
    public List<Torremolinos> getByFecha(@PathVariable Date fecha) {
        return TorremolinosService.findByFecha(fecha);
    }

    @PostMapping
    public ResponseEntity<Torremolinos> create(@RequestBody Torremolinos Torremolinos) {
        Torremolinos.setId(null);
        return ResponseEntity.ok(TorremolinosService.save(Torremolinos));

    }

    @PutMapping("/{id}/condicion")
    public ResponseEntity<Torremolinos> updateCondicion(
            @PathVariable Long id,
            @RequestBody String nuevoCondicion) {
 
        Torremolinos actualizado = TorremolinosService.updateCondicion(id, nuevoCondicion);
 
        if (actualizado == null) {
            // Puede ser porque no existe o porque el estado es inválido
            return ResponseEntity.badRequest().build();
        }
 
        return ResponseEntity.ok(actualizado);
    }
    


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!TorremolinosService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        TorremolinosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

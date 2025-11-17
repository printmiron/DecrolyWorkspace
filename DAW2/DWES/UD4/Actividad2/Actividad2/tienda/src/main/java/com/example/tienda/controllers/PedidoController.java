package com.example.tienda.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.models.Pedido;
import com.example.tienda.repository.PedidoRepository;

//solo que cambie es de PedidoEntity a -> Pedido
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    // GET /pedidos (lista todos los pedidos)

    @GetMapping
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }
    // GET /pedidos/{id} (busca un pedido por id)

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isPresent()) {
            
            return ResponseEntity.ok(pedidoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // POST /pedidos (crea un nuevo pedido)

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        // Nos aseguramos de que venga sin id o lo ignoramos
        pedido.setId(null);
        Pedido guardado = pedidoRepository.save(pedido);
        return ResponseEntity
                .created(URI.create("/pedidos/" + guardado.getId()))
                .body(guardado);
    }
    // DELETE /pedidos/{id} (borra un pedido por id)

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}

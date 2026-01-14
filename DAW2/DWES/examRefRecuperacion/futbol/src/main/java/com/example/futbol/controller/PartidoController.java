package com.example.futbol.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.futbol.service.ArbitrosService;
import com.example.futbol.service.EquipoService;
import com.example.futbol.service.JugadorService;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private JugadorService jugadorService;
    @Autowired
    private EquipoService equipoService;
    @Autowired
    private ArbitrosService arbitrosService;

    // Endpoint: GET /partidos/acta?eq1=1&eq2=2&arb1=UUID1&arb2=UUID2
    @GetMapping("/acta")
    public Map<String, Object> generarActa(
            @RequestParam Long eq1,
            @RequestParam Long eq2,
            @RequestParam String arb1,
            @RequestParam String arb2) {

        Map<String, Object> acta = new HashMap<>();

        // 1. Obtener información de los Árbitros
        acta.put("arbitro_principal", arbitrosService.findById(arb1));
        acta.put("arbitro_asistente", arbitrosService.findById(arb2));

        // 2. Obtener nombres de los equipos para buscar a los jugadores
        String nombreEq1 = equipoService.findById(eq1).getNombreEquipo();
        String nombreEq2 = equipoService.findById(eq2).getNombreEquipo();

        // 3. Obtener plantillas (jugadores) de cada equipo
        acta.put("plantilla_" + nombreEq1, jugadorService.findByEquipo(nombreEq1));
        acta.put("plantilla_" + nombreEq2, jugadorService.findByEquipo(nombreEq2));

        return acta;
    }
}

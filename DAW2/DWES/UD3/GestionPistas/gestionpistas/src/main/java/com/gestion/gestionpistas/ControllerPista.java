package com.gestion.gestionpistas;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller

public class ControllerPista {
    
    private ArrayList<Pista> pistas = new ArrayList<>();
    
    @GetMapping ("/")
    public String formularioPista(Model model) {
        model.addAttribute("pistas", pistas);
        return "index";
    }

    @PostMapping("/addPista")
    public String addPista (@RequestParam ("nombrePista") String nombrePistas, 
                            @RequestParam("horas") String horasDisponibles,
                            Model model) {
        Pista newPista = new Pista(nombrePistas, horasDisponibles);
        pistas.add(newPista);
        model.addAttribute("pistas", pistas);
        
        return "index";
    }
    


    
    

}

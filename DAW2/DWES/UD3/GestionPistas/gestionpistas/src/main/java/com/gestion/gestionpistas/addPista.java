package com.gestion.gestionpistas;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.model.Model;


@Controller

public class addPista {
    
    private ArrayList<Pista> pistas = new ArrayList<>();
    
    @GetMapping ("/addPista")
    public String formPista(Model model) {
        return "index.html";
    }

    @PostMapping("/addPista")
     public String guardarPista(@ModelAttribute Pista pista, RedirectAttributes redirectAttributes) {
       
        System.out.println("Nombre: " + pista.getNombrePista());
        System.out.println("Ubicación: " + pista.getHorasDisponibles());

       
        redirectAttributes.addFlashAttribute("mensaje", "Pista guardada con éxito");
        return "index.html"; 
    }
    
    

}

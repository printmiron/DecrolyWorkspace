package com.gestion.gestionpistas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller

public class addPista {
    
    private ArrayList<Pista> listaPistas = new ArrayList<>();
    
    @GetMapping ("/")
    public String formularioPista(Model model) {
        model.addAttribute("pistas", listaPistas);
        return "index";
    }

    @PostMapping("/addPista")
    public String addPista (@RequestParam ("nombrePista") @RequestParam("horas")) {
        
        
        return entity;
    }
    

   
    
    

}

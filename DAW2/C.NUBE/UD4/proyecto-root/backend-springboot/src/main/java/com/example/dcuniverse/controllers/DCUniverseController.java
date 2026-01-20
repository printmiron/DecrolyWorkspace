package com.example.dcuniverse.controllers;

import com.example.dcuniverse.model.Characters;
import com.example.dcuniverse.model.Powerstats;
import com.example.dcuniverse.service.CharacterService;
import com.example.dcuniverse.service.PowerStatsService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/api")
public class DCUniverseController {

    CharacterService characterService;
    PowerStatsService powerStatsService;

    @GetMapping(value = "/characters")
    public ResponseEntity<List<Characters>> getCharacters(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size
    ) {
        
        return ResponseEntity.ok(characterService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping(value = "/characters2")
    public ResponseEntity<Page<Characters>> getCharacters2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        
        return ResponseEntity.ok(characterService.obtenerCharacters(page, size));
    }

    @PostMapping("/characters")
    public Characters createCharacter(@RequestBody Characters character) {
        
        return characterService.save(character);
    }

    @PutMapping("/characters")
    public Characters updateCharacter(@RequestBody Characters character) {
        
        return characterService.save(character);
    }

    @GetMapping(value = "/characters/{id}")
    public ResponseEntity<Characters> getCharacterById(@PathVariable("id") String id) {
        
        Optional<Characters> characterOptional = characterService.findById(id);

        // Verificar si el personaje está presente
        if (characterOptional.isPresent()) {
            return ResponseEntity.ok(characterOptional.get()); // Devolver el personaje encontrado con estado 200 OK
        } else {
           
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devolver 404 si no se encuentra el personaje
        }
    }

    @GetMapping(value = "/characters/name/{heroName}")
    public ResponseEntity<List<Characters>> getCharacterById(
            @PathVariable("heroName") String heroName, // <--- Añadir "heroName"
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size) {
        
        List<Characters> charactersList = characterService.findByHeronameContains(heroName, PageRequest.of(page, size));

        // Verificar si el personaje está presente
        if (!charactersList.isEmpty()) {
            return ResponseEntity.ok(charactersList); // Devolver el personaje encontrado con estado 200 OK
        } else {
           
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devolver 404 si no se encuentra el personaje
        }
    }

    @DeleteMapping("/characters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable("id") String id) {
        
        characterService.deleteById(id);
    }

    @GetMapping("/powerstats/power/{value}")
    public ResponseEntity<List<Powerstats>> getPowerGreaterThan(@PathVariable("value") Double value,
           @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "100") int size) {
       
        return ResponseEntity.ok(powerStatsService.findByPowerGreaterThan(value, PageRequest.of(page, size)));
    }

    @GetMapping("/characters/power/{value}")
    public ResponseEntity<List<Characters>> getCharactersPowerGreaterThan(@PathVariable("value") Double value,
            @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "100") int size) {
       
        return ResponseEntity.ok(characterService.findByPowerGreaterThan(value, PageRequest.of(page, size)));
    }

}

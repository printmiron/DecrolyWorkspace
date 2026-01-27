package com.example.dcuniverse.service;

import com.example.dcuniverse.model.Characters;
import com.example.dcuniverse.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CharacterService {
    @Autowired
    CharacterRepository characterRepository;

    public Page<Characters> obtenerCharacters(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return characterRepository.findAll(pageable);
    }

    public List<Characters> findAll(PageRequest pageable) {

        return characterRepository.findAll(pageable).getContent();
    }

    public Characters save(Characters character) {

        return characterRepository.save(character);
    }


    public Optional<Characters> findById(String id) {

        return characterRepository.findById(id);
    }

    public List<Characters> findByHeronameContains(String heroname, PageRequest pageable) {
        return characterRepository.findByHeronameContains(heroname, pageable);
    }

    public List<Characters> findByAlignment(String alignment) {

        return characterRepository.findByAlignment(alignment);
    }

    public void deleteById(String id) {

        characterRepository.deleteById(id);
    }


    public List<Characters> findByPowerGreaterThan (Double value, PageRequest pageable) {
        return characterRepository.findByPowerstats_PowerGreaterThan(value, pageable);
    }

}

package com.example.dcuniverse.service;

import com.example.dcuniverse.model.Characters;
import com.example.dcuniverse.model.Powerstats;
import com.example.dcuniverse.repository.CharacterRepository;
import com.example.dcuniverse.repository.PowerStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class PowerStatsService {
    PowerStatsRepository powerStatsRepository;


    public List<Powerstats> findByPowerGreaterThan(Double value, Pageable pageable) {
        return powerStatsRepository.findByPowerGreaterThan(value, pageable);

    }


}

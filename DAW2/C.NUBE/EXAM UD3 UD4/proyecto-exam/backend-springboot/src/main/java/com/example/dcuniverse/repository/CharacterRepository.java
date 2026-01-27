package com.example.dcuniverse.repository;

import com.example.dcuniverse.model.Characters;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CharacterRepository extends JpaRepository<Characters, String> {

    List<Characters> findByHeronameContains(@Param("heroname") String heroname, PageRequest pageable);

    List<Characters> findByAlignment(String alignments);

    List<Characters> findByPowerstats_PowerGreaterThan(Double value, PageRequest pageable);
}

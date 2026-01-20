package com.example.dcuniverse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "powerstats")
public class Powerstats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "ID único de las estadísticas (UUID).", example = "a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6")
    private String id;
    @Column(name = "intelligence")
    private Double intelligence;
    @Column(name = "strength")
    private Double strength;
    @Column(name = "speed")
    private Double speed;
    @Column(name = "durability")
    private Double durability;
    @Column(name = "power")
    private Double power;
    @Column(name = "combat")
    private Double combat;
    @OneToOne
    @JoinColumn(name = "characters_id", referencedColumnName = "id")
    @JsonBackReference
    private Characters characters;

}

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de las estadísticas de poder. Autoincremental en bbdd. NO requerido en creación", example = "1")
    private Long id;
    @Column(name="intelligence")
    private Double intelligence;
    @Column(name="strength")
    private Double strength;
    @Column(name="speed")
    private Double speed;
    @Column(name="durability")
    private Double durability;
    @Column(name="power")
    private Double power;
    @Column(name="combat")
    private Double combat;
    @OneToOne
    @JoinColumn(name = "characters_id" , referencedColumnName = "id")
    @JsonBackReference
    private Characters characters;

}

package com.example.futbol.entity;

import java.util.UUID;

import com.example.futbol.Posicion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "dorsal")
    private Integer Dorsal;

    @Column(name = "nombre")
    private String Nombre;

    @Column(name = "apellido1")
    private String Apellido1;

    @Column(name = "apellido2")
    private String Apellido2;

    @Enumerated(EnumType.STRING)
    @Column(name = "posicion")
    private Posicion Posicion;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;


    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDorsal() {
        return Dorsal;
    }

    public void setDorsal(Integer Dorsal) {
        this.Dorsal = Dorsal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }

    public Posicion getPosicion() {
        return Posicion;
    }

    public void setPosicion(Posicion Posicion) {
        this.Posicion = Posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    


}

package com.example.centro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aulas")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nombre_aula")
    private String NombreAula;

    @Column(name = "capacidad")
    private Integer Capacidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreAula() {
        return NombreAula;
    }

    public void setNombreAula(String NombreAula) {
        this.NombreAula = NombreAula;
    }

    public Integer getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(Integer Capacidad) {
        this.Capacidad = Capacidad;
    }

    
}

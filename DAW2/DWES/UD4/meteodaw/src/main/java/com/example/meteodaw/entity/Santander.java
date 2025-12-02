package com.example.meteodaw.entity;

import java.sql.Date;

import com.example.meteodaw.Condicion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

    
@Entity
@Table(name = "Santander")
public class Santander{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private Date fecha;


    @Column(name = "max_temp")
    private Double maxTemp;

    @Column(name = "min_temp")
    private Double minTemp;

    @Column(name = "media_temp")
    private Double mediaTemp;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicion")
    private Condicion condicion;

    @Column(name = "humedad")
    private int humedad;

    @Column(name = "velocidad")
    private int velocidadV;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMediaTemp() {
        return mediaTemp;
    }

    public void setMediaTemp(Double mediaTemp) {
        this.mediaTemp = mediaTemp;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public int getVelocidadV() {
        return velocidadV;
    }

    public void setVelocidadV(int velocidadV) {
        this.velocidadV = velocidadV;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}


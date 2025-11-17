package com.example.morosos.models;

import java.sql.Date;

import com.example.morosos.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "moroso")
public class Moroso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombreMoroso;

    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "cantidad_adeudada", nullable = false)
    private Double cantidadAdeudada;

    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PENDIENTE','PAGADO')")
    private Estado estadoPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMoroso() {
        return nombreMoroso;
    }

    public void setNombreMoroso(String nombreMoroso) {
        this.nombreMoroso = nombreMoroso;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getCantidadAdeudada() {
        return cantidadAdeudada;
    }

    public void setCantidadAdeudada(Double cantidadAdeudada) {
        this.cantidadAdeudada = cantidadAdeudada;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Estado getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(Estado estadoPago) {
        this.estadoPago = estadoPago;
    }


    //getters y setters

   

    



    
    



    
}

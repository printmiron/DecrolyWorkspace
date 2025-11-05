package com.ejemplo.morosos.model;

import java.math.BigDecimal;

public class Moroso {

    private Long id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private BigDecimal importe;
    private String concepto;

    public Moroso() {

    }

    public Moroso(Long id, String nombre, String dni, String email, String telefono,
                  BigDecimal importe, String concepto) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.importe = importe;
        this.concepto = concepto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }


    @Override
    public String toString() {
        return "Moroso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", importe=" + importe +
                ", concepto='" + concepto + '\'' +
                '}';
    }
}

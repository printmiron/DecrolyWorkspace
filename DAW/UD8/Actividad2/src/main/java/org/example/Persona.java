package org.example;

import java.time.LocalDate;

public class Persona {

    private String dni;
    private String nombre;
    private String direccion;
    private LocalDate fechaNacimiento;

    public Persona(String direccion, String dni, LocalDate fechaNacimiento, String nombre) {
        this.direccion = direccion;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
    }

    public Persona(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", fechaNacimiento="
                + fechaNacimiento + "]";
    }

    

}

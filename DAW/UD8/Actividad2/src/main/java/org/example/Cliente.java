package org.example;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Cliente extends Persona {

    private String numSocio;
    private LocalDate fechaBaja;
    List <Articulo> articulosAlquilados = new LinkedList<>();

    public Cliente(LocalDate fechaBaja, String numSocio, String direccion, String dni, LocalDate fechaNacimiento, String nombre) {
        super(direccion, dni, fechaNacimiento, nombre);
        this.fechaBaja = fechaBaja;
        this.numSocio = numSocio;
        this.articulosAlquilados = new LinkedList<>();
    }

    public Cliente(String dni, String numSocio) {
        super(dni);
        this.numSocio = numSocio;
    }


    public String getNumSocio() {
        return numSocio;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public List<Articulo> getArticulosAlquilados() {
        return articulosAlquilados;
    }

    @Override
    public String toString() {
        return "Cliente [numSocio=" + numSocio + ", fechaBaja=" + fechaBaja + ", dni=" + super.getDni() + "]";
    }
    
    public String mostrarArticulosAlquilados() {
        return String.format("Articulos alquilados: ", this.articulosAlquilados);
    }

}

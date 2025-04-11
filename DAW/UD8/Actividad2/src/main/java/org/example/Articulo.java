package org.example;

import java.time.LocalDate;

public class Articulo {

    private String cod;
    private String titulo;
    private LocalDate fechaRegistro;
    private LocalDate fechaBaja;

    
    public Articulo(String cod, String titulo, LocalDate fechaRegistro, LocalDate fechaBaja) {
        this.cod = cod;
        this.titulo = titulo;
        this.fechaRegistro = fechaRegistro;
        this.fechaBaja = fechaBaja;
    }

    public String getCod() {
        return cod;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }
    


    @Override
    public String toString() {
        return "Articulo [cod=" + cod + ", titulo=" + titulo + ", fechaRegistro=" + fechaRegistro + ", fechaBaja="
                + fechaBaja + "]";
    }

    
    
    

}

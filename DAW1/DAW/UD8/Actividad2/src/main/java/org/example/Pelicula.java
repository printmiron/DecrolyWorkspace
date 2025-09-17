package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pelicula extends Articulo {

    private GeneroPeli generoPeli;
    private LocalDateTime fechaAlquilier;
    private boolean isAlquilada;

    

    public Pelicula(String cod, GeneroPeli generoPeli) {
        super(cod);
        this.generoPeli = generoPeli;
        this.fechaAlquilier = LocalDateTime.now();
        this.isAlquilada = false;
    }

    public GeneroPeli getGenero() {
        return generoPeli;
    }

    public LocalDateTime getFechaAlquilier() {
        return fechaAlquilier;
    }

    public boolean isAlquilada() {
        return isAlquilada;
    }

    public void setIsAlquilada(boolean isAlquilada) {
        this.isAlquilada = isAlquilada;
    }

    public void setFechaAlquilier(LocalDateTime fechaAlquilier) {
        this.fechaAlquilier = fechaAlquilier;
    }

    
    @Override
    public String toString() {
        return super.toString() + "Pelicula [genero=" + generoPeli + ", fechaAlquilier=" + fechaAlquilier + ", isAlquilada=" + isAlquilada + "]";

    }

    
    

}

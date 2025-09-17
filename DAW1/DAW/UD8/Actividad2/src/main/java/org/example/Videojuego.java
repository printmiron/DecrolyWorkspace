package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Videojuego extends Articulo {

    private GeneroJuego generoJuego;
    private LocalDateTime fechaAlquilier;
    private boolean isAlquilada;

    

    public Videojuego(String cod, GeneroJuego generoJuego) {
        super(cod);
        this.generoJuego = generoJuego;
        this.fechaAlquilier = LocalDateTime.now();
        this.isAlquilada = false;
    }

    public GeneroJuego getGeneroJuego() {
        return generoJuego;
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
        return super.toString() + "VideoJuego [genero=" + generoJuego + ", fechaAlquilier=" + fechaAlquilier + ", isAlquilada=" + isAlquilada + "]";
    }

    

}

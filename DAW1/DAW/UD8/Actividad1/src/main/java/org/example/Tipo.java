package org.example;

public class Tipo {

    private int id_tipo;
    private String nombre;


    public Tipo(int id_tipo, String nombre) {
        this.id_tipo = id_tipo;
        this.nombre = nombre;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "id_tipo=" + id_tipo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

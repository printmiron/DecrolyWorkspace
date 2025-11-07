package com.example.agenda.model;

public class Contacto {
    private Long id;          // Requerido en el POST (no se autogenera)
    private String nombre;    // Requerido
    private String telefono;  // Opcional
    private String email;     // Opcional
    private String etiqueta;  // Opcional
    private String notas;     // Opcional

    public Contacto() {}

    public Contacto(Long id, String nombre, String telefono, String email, String etiqueta, String notas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.etiqueta = etiqueta;
        this.notas = notas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEtiqueta() { return etiqueta; }
    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}

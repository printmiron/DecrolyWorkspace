package Model;

public class VideoJuego {

    private int id;
    private String nombre_consola;
    private String nombre;
    private GeneroV genero;
    private String desarrollador;
    private double precio;
    private double puntuacionGlobal;

    public VideoJuego(int id, String nombre_consola, String nombre, GeneroV genero, String desarrollador, double precio) {
        this.id = id;
        this.nombre_consola = nombre_consola;
        this.nombre = nombre;
        this.genero = genero;
        this.desarrollador = desarrollador;
        this.precio = precio;
    }

    public VideoJuego(int id, String id_consola, String nombre, GeneroV genero, String desarrollador, double precio, double puntuacionGlobal) {
        this.id = id;
        this.nombre_consola = nombre_consola;
        this.nombre = nombre;
        this.genero = genero;
        this.desarrollador = desarrollador;
        this.precio = precio;
        this.puntuacionGlobal = puntuacionGlobal;
    }

    public VideoJuego() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_Consola() {
        return nombre_consola;
    }

    public void setNombre_consola(String nombre_consola) {
        this.nombre_consola = nombre_consola;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GeneroV getGenero() {
        return genero;
    }

    public void setGenero(GeneroV genero) {
        this.genero = genero;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPuntuacionGlobal() {
        return puntuacionGlobal;
    }

    public void setPuntuacionGlobal(double puntuacionGlobal) {
        this.puntuacionGlobal = puntuacionGlobal;
    }

    @Override
    public String toString() {
        return "VideoJuego{" +
                "id='" + id + '\'' +
                ", Consola ='" + nombre_consola + '\'' +
                ", nombre='" + nombre + '\'' +
                ", genero=" + genero +
                ", desarrollador='" + desarrollador + '\'' +
                ", precio=" + precio +
                '}';
    }



}

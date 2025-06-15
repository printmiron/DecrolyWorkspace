package Model;

import java.time.LocalDate;

public class Valoracion_Empresa {

    private int id;
    private int idVideojuego;
    private int cifEmpresa;
    private int puntuacion;
    private String comentario;
    private LocalDate fechaValoracion;

    public Valoracion_Empresa(int id, int idVideojuego, int cifEmpresa, int puntuacion, String comentario, LocalDate fechaValoracion) {
        this.id = id;
        this.idVideojuego = idVideojuego;
        this.cifEmpresa = cifEmpresa;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fechaValoracion = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public int getCifEmpresa() {
        return cifEmpresa;
    }

    public void setCifEmpresa(int cifEmpresa) {
        this.cifEmpresa = cifEmpresa;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(LocalDate fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }

    @Override
    public String toString() {
        return "Valoracion_Empresa{" +
                "id=" + id +
                ", idVideojuego=" + idVideojuego +
                ", cifEmpresa=" + cifEmpresa +
                ", puntuacion=" + puntuacion +
                ", comentario='" + comentario + '\'' +
                ", fechaValoracion='" + fechaValoracion + '\'' +
                '}';
    }
}

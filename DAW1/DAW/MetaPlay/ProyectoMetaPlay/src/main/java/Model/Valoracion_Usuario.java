package Model;

import java.time.LocalDate;

public class Valoracion_Usuario {

    private int id;
    private int idVideojuego;
    private int idUsuario;
    private int puntuacion;
    private String comentario;
    private LocalDate fechaValoracion;

    public Valoracion_Usuario(int id, int idVideojuego, int idUsuario, int puntuacion, String comentario, LocalDate fechaValoracion) {
        this.id = id;
        this.idVideojuego = idVideojuego;
        this.idUsuario = idUsuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fechaValoracion = LocalDate.now();
    }

    public Valoracion_Usuario(int idVideojuego, int idUsuario, int puntuacion, String comentario) {
        this.idVideojuego = idVideojuego;
        this.idUsuario = idUsuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        return "Valoracion_Usuario{" +
                "id=" + id +
                ", idVideojuego=" + idVideojuego +
                ", idUsuario=" + idUsuario +
                ", puntuacion=" + puntuacion +
                ", comentario='" + comentario + '\'' +
                ", fechaValoracion=" + fechaValoracion +
                '}';
    }
}

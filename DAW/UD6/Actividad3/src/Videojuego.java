import java.time.LocalDate;
import java.time.LocalDateTime;

public class Videojuego extends Articulo {

    private GeneroJuego generoJuego;
    private LocalDateTime fechaAlquilier;
    private boolean isAlquilada;

    

    public Videojuego(String cod, String titulo, LocalDate fechaRegistro, LocalDate fechaBaja,
            GeneroJuego generoJuego, LocalDateTime fechaAlquilier, boolean isAlquilada) {
        super(cod, titulo, fechaRegistro, fechaBaja);
        this.generoJuego = generoJuego;
        this.fechaAlquilier = fechaAlquilier;
        this.isAlquilada = isAlquilada;
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
        return "VideoJuego [genero=" + generoJuego + ", fechaAlquilier=" + fechaAlquilier + ", isAlquilada=" + isAlquilada
                + "]";
    }

    

}

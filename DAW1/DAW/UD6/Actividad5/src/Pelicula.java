
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pelicula extends Articulo {

    private GeneroPeli generoPeli;
    private LocalDateTime fechaAlquilier;
    private boolean isAlquilada;

    

    public Pelicula(String cod, String titulo, LocalDate fechaRegistro, LocalDate fechaBaja,
            GeneroPeli generoPeli, LocalDateTime fechaAlquilier, boolean isAlquilada) {
        super(cod, titulo, fechaRegistro, fechaBaja);
        this.generoPeli = generoPeli;
        this.fechaAlquilier = fechaAlquilier;
        this.isAlquilada = isAlquilada;
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


//Necesito hacer el ENUM (generos de peliculas)
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pelicula {

    private static int contador = 0;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private int id;
    private String titulo;
    private Genero genero;
    private LocalDate fechaRegistro;
    private LocalDate fechaBaja;
    private LocalDateTime fechaAlquiler;
    private boolean esAlquilada;

    public Pelicula(int id, String titulo, Genero genero) {
        this.id = ++contador;
        this.titulo = titulo;
        this.genero = genero;
        this.fechaRegistro = LocalDate.now();
        this.fechaBaja = LocalDate.now();
        this.fechaAlquiler = LocalDateTime.now();
        this.esAlquilada = false;
    }

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
    }

    public LocalDate getFechaBaja() {
        return this.fechaBaja;
    }

    public LocalDateTime getFechaAlquiler() {
        return this.fechaAlquiler;
    }

    public boolean getAlquilada() {
        return this.esAlquilada;
    }

    public void setAlquilada(boolean alquilada) {
        this.esAlquilada = alquilada;
    }

    public String mostrarInfoPelicula() {
        return String.format("ID: %s, Titulo: %s, Fecha Registro: %s, Fecha Baja: %s, Fecha Aqulier: %s, Alquilada: %s",
                this.id, this.titulo, this.fechaRegistro, this.fechaBaja, this.fechaAlquiler, this.esAlquilada);
    }

}

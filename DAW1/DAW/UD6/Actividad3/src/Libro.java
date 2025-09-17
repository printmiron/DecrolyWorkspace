
import java.io.Serializable;
import java.time.LocalDate;

public class Libro implements Serializable{
    private static final long serialVersionUID = 1L;

    private String isbn;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    
    public Libro(String iSBN, String titulo, String autor, LocalDate fechaPublicacion) {
        this.isbn = iSBN;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public LocalDate getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public void setIsbn(String iSBN) {
        this.isbn = iSBN;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }




    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", fechaPublicacion="
                + fechaPublicacion + "]";
    }
    
    
    

}

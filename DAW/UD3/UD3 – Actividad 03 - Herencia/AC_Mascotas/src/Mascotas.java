
import java.time.LocalDate;

public abstract class Mascotas {

    String nombre;
    int edad;
    boolean estado;
    LocalDate fechaNacimiento;

    public Mascotas(String nombre, int edad, boolean estado, LocalDate fechaNacimiento) {
        this.edad = edad;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public abstract String getTipo();

    public String muestraMascota() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Estado: " + (estado ? "Saludable" : "Enfermo") + ", Fecha de Nacimiento: " + fechaNacimiento;
    }

}


import java.time.LocalDate;

/**
 * Clase abstracta que representa una mascota.
 * Contiene atributos como nombre, edad, estado de salud y fecha de nacimiento.
 * 
 * @author Pavel Miron
 * @version 1.0
 * @since 19-02-2025
 */

public abstract class Mascotas {

    String nombre;
    int edad;
    boolean estado;
    LocalDate fechaNacimiento;

    /**
     * Constructor de la clase Mascotas.
     * 
     * @param nombre Nombre de la mascota
     * @param edad Edad de la mascota
     * @param estado Estado de salud de la mascota (true si está saludable, false si está enferma)
     * @param fechaNacimiento Fecha de nacimiento de la mascota
     */
    
    public Mascotas(String nombre, int edad, boolean estado, LocalDate fechaNacimiento) {
        this.edad = edad;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el nombre de la mascota.
     * 
     * @return Nombre de la mascota
     */
    
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene la edad de la mascota.
     * 
     * @return Edad de la mascota
     */
    public int getEdad() {
        return this.edad;
    }

    /**
     * Obtiene el estado de salud de la mascota.
     * 
     * @return true si la mascota está saludable, false si está enferma
     */
    
    public boolean isEstado() {
        return this.estado;
    }

    /**
     * Obtiene la fecha de nacimiento de la mascota.
     * 
     * @return Fecha de nacimiento de la mascota
     */
    
    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * Metodo abstracto implementado por las subclases para definir el tipo de mascota
     * 
     * @return tipo de mascota (Perro, Gato, Loro, etc.)
     */
    
    public abstract String getTipo();

    /**
     * Devuelve una representación en cadena de la mascota con sus detalles.
     * 
     * @return Cadena con la informacion de la mascota
     */
    
    public String muestraMascota() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Estado: " + (estado ? "Saludable" : "Enfermo") + ", Fecha de Nacimiento: " + fechaNacimiento;
    }

}

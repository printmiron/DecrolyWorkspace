
import java.time.LocalDate;

/**
 * Representa un perro, una subclase de Mascotas.
 * 
 * @author Pavel Miron
 * @version 1.0
 * @since 19-02-2025
 */

public class Perro extends Mascotas {

    String raza;
    boolean pulgas;

    /**
     * Constructor de la clase Canario.
     * 
     * @param nombre Nombre del canario.
     * @param edad Edad del canario.
     * @param estado Estado de salud del canario.
     * @param fechaNacimiento Fecha de nacimiento del canario.
     * @param raza CRaza del perro.
     * @param pulgas Si tiene pulgas el perro.
     */
    
    public Perro(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String raza, boolean pulgas) {
        super(nombre, edad, estado, fechaNacimiento);
        this.raza = raza;
        this.pulgas = pulgas;
    }

    public String getRaza() {
        return this.raza;
    }

    public boolean isPulgas() {
        return this.pulgas;
    }

    @Override
    public String muestraMascota() {
        return super.muestraMascota() + ", Raza: " + raza + ", Tiene Pulgas: " + (pulgas ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Perro";
    }

    public String hablaPerro() {
        return "GAF GAF GAF";
    }

}

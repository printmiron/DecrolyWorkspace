
import java.time.LocalDate;
/**
 * Clase abstracta que representa una Ave que se extienda a Mascota.
 * Contiene atributos como pico y vuela.
 * 
 * @author Pavel Miron
 * @version 1.0
 * @since 19-02-2025
 */
public abstract class Aves extends Mascotas {

    String pico;
    boolean vuela;

    /**
     * Constructor de la clase Aves.
     * 
     * 
     * @param pico Descripcion del pico
     * @param vuela true si vuela, false si no vuela
     */
    
    public Aves(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String pico, boolean vuela) {
        super(nombre, edad, estado, fechaNacimiento);
        this.pico = pico;
        this.vuela = vuela;
    }

    
    /**
     * Obtiene el pico del Ave.
     * 
     * @return Pico del ave
     */
    
    public String getPico() {
        return this.pico;
    }

    /**
     * Obtiene si puede volar el ave.
     * 
     * @return Vuela si o no
     */
    
    public boolean isVuela() {
        return this.vuela;
    }

    

}

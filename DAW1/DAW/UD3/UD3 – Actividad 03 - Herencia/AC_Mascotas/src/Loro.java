
import java.time.LocalDate;
/**
 * Representa un loro, una subclase de Aves.
 * 
 * @author Pavel Miron
 * @version 1.0
 * @since 19-02-2025
 */
public class Loro extends Aves{

    String origen;
    boolean habla;

    /**
     * Constructor de la clase Canario.
     * 
     * @param nombre Nombre del canario.
     * @param edad Edad del canario.
     * @param estado Estado de salud del canario.
     * @param fechaNacimiento Fecha de nacimiento del canario.
     * @param pico Tipo de pico del canario.
     * @param vuela Indica si el canario puede volar.
     * @param origen Origen del Loro
     * @param habla Indica si habla o no
     */
    
    public Loro(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String pico, boolean vuela, String origen, boolean habla) {
        super(nombre, edad, estado, fechaNacimiento, pico, vuela);
        this.origen = origen;
        this.habla = habla;
    }

    
    /**
     * Obtiene el origen del Loro.
     * 
     * @return Origen del loro.
     */
    public String getOrigen() {
        return this.origen;
    }

    /**
     * Obtiene si puede hablar o no.
     * 
     * @return true si canta, false si no canta
     */
    
    public boolean isHabla() {
        return this.habla;
    }

    /**
     * Muestra la informacion detallada del loro.
     * 
     * @return Informacion con los datos del loro.
     */

    @Override
    public String muestraMascota() {
        return super.muestraMascota() + ", Pico: " + pico + ", Puede Volar: " + (vuela ? "Sí" : "No") + ", Origen: " + origen + ", Puede Hablar: " + (habla ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Loro";
    }

    /**
     * Representa el sonido caracteristico de un loro.
     * 
     * @return Sonido del loro.
     */
    
    public String saludaLoro() {
        return "Gritar";
    }

}


import java.time.LocalDate;
/**
 * Representa un gato, una subclase de Mascotas.
 * 
 * @author Pavel Miron
 * @version 1.0
 * @since 19-02-2025
 */
public class Gato extends Mascotas {

    String color;
    boolean peloLargo;

    /**
     * Constructor de la clase Canario.
     * 
     * @param nombre Nombre del canario.
     * @param edad Edad del canario.
     * @param estado Estado de salud del canario.
     * @param fechaNacimiento Fecha de nacimiento del canario.
     * @param color Color del gato.
     * @param peloLargo Tipo de pelo del gato.
     */
    
    public Gato(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String color, boolean peloLargo) {
        super(nombre, edad, estado, fechaNacimiento);
        this.color = color;
        this.peloLargo = peloLargo;
    }

    
    /**
     * Obtiene el color del gato.
     * 
     * @return Color del gato.
     */

    public String getColor() {
        return this.color;
    }

    /**
     * Obtiene el tipo de pelo del gato.
     * 
     * @return true si tiene pelo largo, false si no.
     */
    
    public boolean isPeloLargo() {
        return this.peloLargo;
    }

    /**
     * Muestra la informacion detallada del gato.
     * 
     * @return Informacion con los datos del gato.
     */
    
    @Override
    public String muestraMascota() {
        return super.muestraMascota() + ", Color: " + color + ", Pelo Largo: " + (peloLargo ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Gato";
    }

    /**
     * Representa el sonido caracteristico del gato.
     * 
     * @return Sonido del gato.
     */
    
    public String hablaGato() {
        return "MIAU MIAU MIAU";
    }

    

}

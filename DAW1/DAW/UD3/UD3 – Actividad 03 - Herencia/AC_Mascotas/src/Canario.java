import java.time.LocalDate;

/**
 * Representa un canario, una subclase de Aves.
 * 
 * @author Pavel Miron
 * @version 1.0
 * @since 19-02-2025
 */
public class Canario extends Aves {

    private String color;
    private boolean canta;

    /**
     * Constructor de la clase Canario.
     * 
     * @param nombre Nombre del canario.
     * @param edad Edad del canario.
     * @param estado Estado de salud del canario.
     * @param fechaNacimiento Fecha de nacimiento del canario.
     * @param pico Tipo de pico del canario.
     * @param vuela Indica si el canario puede volar.
     * @param color Color del canario.
     * @param canta Indica si el canario canta.
     */
    public Canario(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String pico, boolean vuela, String color, boolean canta) {
        super(nombre, edad, estado, fechaNacimiento, pico, vuela);
        this.color = color;
        this.canta = canta;
    }

    /**
     * Obtiene el color del canario.
     * 
     * @return Color del canario.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Indica si el canario canta.
     * 
     * @return true si el canario canta, false en caso contrario.
     */
    public boolean isCanta() {
        return this.canta;
    }

    /**
     * Muestra la informacion detallada del canario.
     * 
     * @return Informacion con los datos del canario.
     */
    
    @Override
    public String muestraMascota() {
        return super.muestraMascota() + ", Pico: " + pico + ", Puede Volar: " + (vuela ? "Sí" : "No") + ", Color: " + color + ", Canta: " + (canta ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Canario";
    }

    /**
     * Representa el sonido caracteristico de un canario.
     * 
     * @return Sonido del canario.
     */
    public String hablaCanario() {
        return "Trinar";
    }
}
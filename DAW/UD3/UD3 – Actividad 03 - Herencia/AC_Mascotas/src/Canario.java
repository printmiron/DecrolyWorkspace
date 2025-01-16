
import java.time.LocalDate;

public class Canario extends Aves{

    String color;
    boolean canta;

    public Canario(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String pico, boolean vuela, String color, boolean canta) {
        super(nombre, edad, estado, fechaNacimiento, pico, vuela);
        this.color = color;
        this.canta = canta;
    }

    

    public String getColor() {
        return this.color;
    }

    public boolean isCanta() {
        return this.canta;
    }

    @Override
    public String muestraMascota() {
        return super.muestraMascota() + ", Pico: " + pico + ", Puede Volar: " + (vuela ? "Sí" : "No") + ", Color: " + color + ", Canta: " + (canta ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Canario";
    }

    public String hablaCanario() {
        return "Trinar";
    }
    

}

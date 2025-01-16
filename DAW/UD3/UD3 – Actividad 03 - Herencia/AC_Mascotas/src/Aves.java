
import java.time.LocalDate;

public abstract class Aves extends Mascotas {

    String pico;
    boolean vuela;

    public Aves(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String pico, boolean vuela) {
        super(nombre, edad, estado, fechaNacimiento);
        this.pico = pico;
        this.vuela = vuela;
    }

    

    public String getPico() {
        return this.pico;
    }

    public boolean isVuela() {
        return this.vuela;
    }

    

}

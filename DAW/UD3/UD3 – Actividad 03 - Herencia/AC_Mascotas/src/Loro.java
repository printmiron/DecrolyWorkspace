
import java.time.LocalDate;

public class Loro extends Aves{

    String origen;
    boolean habla;

    public Loro(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String pico, boolean vuela, String origen, boolean habla) {
        super(nombre, edad, estado, fechaNacimiento, pico, vuela);
        this.origen = origen;
        this.habla = habla;
    }

    

    public String getOrigen() {
        return this.origen;
    }

    public boolean isHabla() {
        return this.habla;
    }


    @Override
    public String muestraMascota() {
        return super.muestraMascota() + ", Pico: " + pico + ", Puede Volar: " + (vuela ? "Sí" : "No") + ", Origen: " + origen + ", Puede Hablar: " + (habla ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Loro";
    }

    public String saludaLoro() {
        return "Gritar";
    }

}

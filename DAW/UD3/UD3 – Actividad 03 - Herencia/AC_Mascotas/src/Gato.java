
import java.time.LocalDate;

public class Gato extends Mascotas {

    String color;
    boolean peloLargo;

    public Gato(String nombre, int edad, boolean estado, LocalDate fechaNacimiento, String color, boolean peloLargo) {
        super(nombre, edad, estado, fechaNacimiento);
        this.color = color;
        this.peloLargo = peloLargo;
    }

    


    public String getColor() {
        return this.color;
    }

    public boolean isPeloLargo() {
        return this.peloLargo;
    }


    @Override
    public String muestraMascota() {
        return super.muestraMascota() + ", Color: " + color + ", Pelo Largo: " + (peloLargo ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Gato";
    }

    public String hablaGato() {
        return "MIAU MIAU MIAU";
    }

    

}


import java.time.LocalDate;

public class Perro extends Mascotas {

    String raza;
    boolean pulgas;

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

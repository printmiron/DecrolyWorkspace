
import java.time.LocalDate;

public class ContactoPersona extends Contacto {

    private LocalDate cumpleanos;

    public ContactoPersona(String nombre, String numeroT, LocalDate cumpleanos) {
        super(nombre, numeroT);
        this.cumpleanos = cumpleanos;
    }

    public LocalDate getCumpleanos() {
        return this.cumpleanos;
    }

    public void setCumpleanos(LocalDate cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    @Override
    public String toString() {
        return super.toString() + " [cumpleanos=" + cumpleanos + "]";
    }

   

}

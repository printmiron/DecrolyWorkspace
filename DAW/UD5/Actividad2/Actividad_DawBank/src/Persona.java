
public class Persona {

    private String nombre;
    private String dni;
    private String fechaNacimiento;

    public Persona(String nombre, String dni, String fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + "]";
    }
    
    
    

}

public class Persona {

    private String nombre;
    private String fechaNacimiento;
    private String dni;
    private String direccion;
    
    public Persona(String nombre, String fechaNacimiento, String dni, String direccion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "[nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + ", direccion="
                + direccion + "]";
    }

    

}

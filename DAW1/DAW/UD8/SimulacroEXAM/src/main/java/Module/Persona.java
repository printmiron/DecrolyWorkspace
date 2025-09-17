package Module;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private Sexo sexo;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correo;
    private String direccion;


    public Persona(String nombre, String apellido, String dni, int edad, Sexo sexo, LocalDate fechaNacimiento, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Persona() {

    }

    public Persona(PersonaBuilder personaBuilder) {
        this.nombre = personaBuilder.getNombre();
        this.apellido = personaBuilder.getApellido();
        this.dni = personaBuilder.getDni();
        this.edad = personaBuilder.getEdad();
        this.sexo = personaBuilder.getSexo();
        this.fechaNacimiento = personaBuilder.getFechaNacimiento();
        this.telefono = personaBuilder.getTelefono();
        this.correo = personaBuilder.getCorreo();
        this.direccion = personaBuilder.getDireccion();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public static PersonaBuilder builder(){
        return new PersonaBuilder();
    }

}

package Module;

import java.time.LocalDate;

public class PersonaBuilder {

    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correo;
    private String direccion;

    public PersonaBuilder() {
    }

    public String getNombre() {
        return nombre;
    }

    public PersonaBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public PersonaBuilder apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public PersonaBuilder dni(String dni) {
        this.dni = dni;
        return this;
    }

    public int getEdad() {
        return edad;
    }

    public PersonaBuilder edad(int edad) {
        this.edad = edad;
        return this;
    }

    public String getSexo() {
        return sexo;
    }

    public PersonaBuilder sexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public PersonaBuilder fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public PersonaBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public PersonaBuilder correo(String correo) {
        this.correo = correo;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public PersonaBuilder direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public Persona build() {
        Persona persona = new Persona(this);
        return persona;
    }
}

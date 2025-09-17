package Module;

public class PersonaBuilder {

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String correo;
    private Tipo tipo;

    PersonaBuilder() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public PersonaBuilder Nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public PersonaBuilder Apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }
    public PersonaBuilder Dni(String dni) {
        this.dni = dni;
        return this;
    }
    public PersonaBuilder Telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }
    public PersonaBuilder Direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }
    public PersonaBuilder Correo(String correo) {
        this.correo = correo;
        return this;
    }

    public PersonaBuilder Tipo(Tipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public Persona build() {
        Persona persona = new Persona(this);
        return persona;
    }


}

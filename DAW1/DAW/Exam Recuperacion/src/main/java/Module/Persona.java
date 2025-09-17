package Module;

public class Persona {

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String correo;


    public Persona(String nombre, String apellido, String dni, String telefono, String direccion, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public Persona() {

    }

    public Persona (PersonaBuilder personaBuilder){
        this.nombre = personaBuilder.getNombre();
        this.apellido = personaBuilder.getApellido();
        this.dni = personaBuilder.getDni();
        this.telefono = personaBuilder.getTelefono();
        this.direccion = personaBuilder.getDireccion();
        this.correo = personaBuilder.getCorreo();
    }

    public Persona(String nombre, String apellido, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = email;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


}

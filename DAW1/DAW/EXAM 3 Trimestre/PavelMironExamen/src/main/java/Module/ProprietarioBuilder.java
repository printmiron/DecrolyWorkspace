package Module;

public class ProprietarioBuilder {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;

    public ProprietarioBuilder() {

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

    public String getEmail() {
        return email;
    }


    public ProprietarioBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
    }
    public ProprietarioBuilder apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }
    public ProprietarioBuilder dni(String dni) {
        this.dni = dni;
        return this;
    }
    public ProprietarioBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }
    public ProprietarioBuilder direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }
    public ProprietarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public Propietario build() {
        Propietario propietario = new Propietario(this);
        return propietario;
    }








}

import java.sql.Date;

public class Cliente extends Persona{

    private String telefono;
    private String email;
    private String direccion;
    public Cliente(String nombre, String dni, Date fechaNacimiento, String telefono, String email, String direccion) {
        super(nombre, dni, fechaNacimiento);
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    public String getDireccion() {
        return direccion;
    }
    
    @Override
    public String toString() {
        return "Cliente [telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + "]";
    }

    
    

}

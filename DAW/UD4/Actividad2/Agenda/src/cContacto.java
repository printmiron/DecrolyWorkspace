

public class cContacto {

    private String nombre;
    private String telefono;
    private String correo;
    
    public cContacto(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public static boolean validarNombre(String nombre){
        return nombre.matches("^[A-Z][a-zA-Z]*$");
    }

    public static boolean validarTelefono(String telefono){
        return telefono.matches("^[67|9]\\d{8}$");
    }

    public static boolean validarCorreo(String correo){
        return correo.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}$");
    }

    @Override
    public String toString() {
        return "Contacto [nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo + "]";
    }

}

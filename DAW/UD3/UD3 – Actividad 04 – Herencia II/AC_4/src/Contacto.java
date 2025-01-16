public class Contacto {

    private String nombre;
    private String numeroT;

    public Contacto(String nombre, String numeroT) {
        this.nombre = nombre;
        this.numeroT = numeroT;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getNumeroT() {
        return this.numeroT;
    }

    @Override
    public String toString() {
        return "Contacto [nombre=" + nombre + ", numeroT=" + numeroT + "]";
    }

    

}

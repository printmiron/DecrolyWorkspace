public abstract  class Contacto {

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setnumeroT(String numeroT) {
        this.numeroT = numeroT;
    }

    

    @Override
    public String toString() {
        return "Contacto [nombre=" + nombre + ", numeroT=" + numeroT + "]";
    }

    

}
